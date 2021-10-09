# createdirs-maven-plugin
Simple Maven plugin used to create directories

## Installation & Configuration
```xml
<plugin>
  <groupId>cuccovillo.alessio.maven</groupId>
  <artifactId>createdirs-maven-plugin</artifactId>
  <version>1.1</version>
  <configuration>
    <skip>false</skip>
    <intermediateDirs>true</intermediateDirs>
    <dirs>
      <dir>${project.build.directory}/non/existent/directory</dir>
      <dir>${project.build.directory}/existent/directory/new/sub/directory</dir>
    </dirs>
  </configuration>
  <executions>
    <execution>
      <goals>
        <goal>create-dirs</goal>
      </goals>
      <phase>package</phase>
    </execution>
  </executions>
</plugin>
```

Create directories with following command:
```shell
mvn package createdirs:create-dirs
```

## About this plugin
I've not found any plugin that can simply create directories.
This plugin does nothing more than this.
Given a list of directories (one or more) you can create your deployment structure.
Do you need that your application has the directories in, work, out created on the target directory under the linux folder?
It's not a problem!
Simply configure the plugin like this:
```xml
<configuration>
  <dirs>
    <dir>${project.build.directory}/linux/in<dir>
    <dir>${project.build.directory}/linux/work<dir>
    <dir>${project.build.directory}/linux/out<dir>
  </dirs> 
</configuration>
```

If the intermediate directory linux does not exist, then will be created for you.
If you need to not create any intermediate directory add the following to the configuration:
```xml
  <intermediateDirs>false</intermediateDirs>
```
