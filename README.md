# DEPENDENCY CLEANER
Dependency cleaner for maven projects (customized for springboot)

Dependency cleaner is a program built for Springboot projects(Maven) to make removing dependencies from your projects easier.
The inspiration struck me while I was at work and saw how our POM file was growing complicated, making it difficult to
maintain and difficult to locate unneeded Dependencies.


 You can get the rar to your machine with the following link
https://github.com/junaidbs/dependency_cleaner/releases/tag/v0.2.0-alpha

# HOW TO USE



To use the Dependency Cleaner tool, follow these steps:

* Download and extract the tool.
* Open a command prompt inside the extracted folder.
* Type the following command: java -jar dependencycleaner-0.0.1-SNAPSHOT.jar {path-to-spring-boot-maven-folder} {optional-spring-boot-service-stop-statement}.
* Replace {path-to-spring-boot-maven-folder} with the path to the folder containing your Spring Boot project's POM file.
* Optionally, add a {optional-spring-boot-service-stop-statement} to stop the Spring Boot service.
# The tool will automate the following steps:
* Remove dependencies.
* Build a JAR file.
* Run the JAR file to ensure that no runtime dependency is missing.
* If the JAR file runs successfully, the dependencies will be removed.
* If the build or run fails, the dependencies will not be removed.

![run_command](https://user-images.githubusercontent.com/70962606/195415362-b98451e9-0fb9-4860-93e0-490d9bdf68fc.jpg)


# OUTPUT

![logs](https://user-images.githubusercontent.com/70962606/195415565-813bf577-e338-44ac-9ccf-968abd717a59.jpg)
If a removed dependency is required for the project, the message "Dependency deletion failed, the removed dependency is still being used" will be displayed.

![logs1](https://user-images.githubusercontent.com/70962606/195415717-1d79042b-0725-4d9a-8da1-0766fdb9693f.jpg)


![logs3](https://user-images.githubusercontent.com/70962606/195415732-71b7ee81-ba4e-4825-a007-68487fac5f91.jpg)
If a removed dependency is not needed for the project, the message "Unused dependency deletion successful" will be displayed.

The tool creates a backup of the original POM file as "backup_pom.xml", allowing you to compare it with the modified POM file later.

JAVA FILE-> https://github.com/junaidbs/dependency_cleaner/blob/v0.2.0-alpha/src/main/java/com/cleaner/dependency/MavenDependencyCleaner.java
