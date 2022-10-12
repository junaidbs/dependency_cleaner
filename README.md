# Dependency_cleaner
Dependency cleaner for maven projects (customized for springboot)

I'm presently working on a Maven-built Java code base that is rather massive. 
There are 12 web apps, 190 Maven modules, and one million Java lines in it. With a code base like this, there is a lot of history. 
I've got the impression that we likely have some unused Maven dependencies for some time. When code is altered and moved around over time, 
this occurs rather frequently. Additionally, increasing new dependents is typically the solution to dependence problems. 
I've seldom ever seen somebody remove dependencies from a build that is already functional.


 You can get the rar to your machine with the following link
https://github.com/junaidbs/dependency_cleaner/releases/tag/v0.2.0-alpha

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

IT WILL CREATE BACKUP POM "backup_pom.xml" SO YOU CAN COMPARE 

JAVA FILE-> https://github.com/junaidbs/dependency_cleaner/blob/v0.2.0-alpha/src/main/java/com/cleaner/dependency/MavenDependencyCleaner.java
