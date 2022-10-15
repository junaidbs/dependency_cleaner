# DEPENDENCY CLEANER
Dependency cleaner for maven projects (customized for springboot)

Dependency cleaner is a program built for Springboot projects(Maven) to make removing dependencies from your projects easier.
The inspiration struck me while I was at work and saw how our POM file was growing complicated, making it difficult to
maintain and difficult to locate unneeded Dependencies.


 You can get the rar to your machine with the following link
https://github.com/junaidbs/dependency_cleaner/releases/tag/v0.2.0-alpha

# HOW ITS WORKS

Automate these steps

* Remove dependency
* Build jar file 
* If ibuild succesfull then 
* Run jar file to ensure no runtime dependency removed
* If jar run succesfully->dependecy will remove
* If build failed or run jar failed  ->dependecy  will not delete

# HOW TO USE
* Download and extract

* Run cmd inside folder

* Type  java -jar dependencycleaner-0.0.1-SNAPSHOT.jar {spring boot project path where pom file located} {spring boot service stop statement(optional)}

* First argument -path to spring boot maven folder where POM located

* Second argument -spring boot service stop statement(optional)

![run_command](https://user-images.githubusercontent.com/70962606/195415362-b98451e9-0fb9-4860-93e0-490d9bdf68fc.jpg)


# OUTPUT

![logs](https://user-images.githubusercontent.com/70962606/195415565-813bf577-e338-44ac-9ccf-968abd717a59.jpg)
If  removed dependency needful for the project then "-----------------used dependency deletion failed--------------------" message show

![logs1](https://user-images.githubusercontent.com/70962606/195415717-1d79042b-0725-4d9a-8da1-0766fdb9693f.jpg)


![logs3](https://user-images.githubusercontent.com/70962606/195415732-71b7ee81-ba4e-4825-a007-68487fac5f91.jpg)
If  removed dependency not needful for the project then "-----------------unused dependency deletion succesfull--------------------" message show

It create a backup pom "backup_pom.xml" so you can compare later

JAVA FILE-> https://github.com/junaidbs/dependency_cleaner/blob/v0.2.0-alpha/src/main/java/com/cleaner/dependency/MavenDependencyCleaner.java
