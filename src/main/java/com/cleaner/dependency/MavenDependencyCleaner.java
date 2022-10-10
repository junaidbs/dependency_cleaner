package com.cleaner.dependency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.json.JSONException;

/**
 * @author JUNAID B S
 *
 */
public class MavenDependencyCleaner
{

	static String line = "";
	static String str = "";
	static String projectPath;
	static String jarEndString = "Tomcat started on port(s):";

	public static void main(String[] args)
			throws JSONException, MavenInvocationException, java.io.IOException, XmlPullParserException
	{
		if (args.length < 1)
		{
			System.out.println("invalid number of arguments project path (folder contains pom)  required");
			System.exit(0);

		}
		projectPath = args[0];
		if (args.length == 2)
		{
			// customized jar stop statement
			jarEndString = args[1];
		}

		try
		{
			// creating backup pom
			File src = new File(projectPath + "pom.xml");
			File dest = new File(projectPath + "backup_pom.xml");
			Files.copy(src.toPath(), dest.toPath());
		} catch (Exception e)
		{
			// exception will throw if backup file already exist

		}

		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader(projectPath + "pom.xml"));
		List<Dependency> dependencies = model.getDependencies();
		List<Dependency> tempDependencies = new ArrayList<>(dependencies);
		String jarName = model.getArtifactId() + "-" + model.getVersion() + ".jar";

		for (Dependency dependency : tempDependencies)

		{
			System.out.println(
					"\n ------------------------Dependency: " + dependency.getArtifactId() + "----------------------");
			model.removeDependency(dependency);
			savepom(model);
			try
			{
				if (!(buildJar() && checkJarRunnable(jarName)))
				{
					model.addDependency(dependency);
					savepom(model);
					System.out.println("-----------------used dependency deletion failed--------------------");
				} else
				{
					System.out.println("------------------ unused dependency deletion succesfull-------------------");
				}

			} catch (MavenInvocationException e)
			{
				e.printStackTrace();
				model.addDependency(dependency);
				savepom(model);
			}
		}

	}

	private static void savepom(Model model)
	{
		try
		{
			new MavenXpp3Writer().write(new FileOutputStream(new File(projectPath, "pom.xml")), model);
		} catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}

	private static boolean buildJar() throws MavenInvocationException
	{
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File(projectPath, "pom.xml"));

		request.setGoals(Collections.singletonList("clean install -DskipTests"));

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File("apache-maven-3.8.6"));
		InvocationResult result = invoker.execute(request);

		if (result.getExitCode() != 0)
		{
			return false;
		} else
		{
			System.out.println("-------------------jar builded successfully--------------------");
			return true;
		}
	}

	private static boolean checkJarRunnable(String jarName) throws java.io.IOException
	{

		ProcessBuilder processBuilder = new ProcessBuilder();

		// Run a command
		processBuilder.command("java", "-jar", projectPath + "/target/" + jarName);

		try
		{
			boolean succesflag = false;

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null)
			{
				output.append(line + "\n");
				System.out.println(line);
				if (line.contains(jarEndString))
				{
					succesflag = true;
					process.destroy();
				}
			}
			return succesflag;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
