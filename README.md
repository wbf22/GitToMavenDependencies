

Use this plugin as so:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.gmd</groupId>
            <artifactId>plugin</artifactId> <!-- Change to your artifactId -->
            <version>1.0.0</version> <!-- Change to your version -->
            <configuration>
                <repositories>
                    <repository>
                        <gitRepoUrl>https://github.com/user1/repo1.git</gitRepoUrl>
                        <branch>main</branch>
                    </repository>
                    <repository>
                        <gitRepoUrl>https://github.com/user2/repo2.git</gitRepoUrl>
                        <branch>develop</branch>
                    </repository>
                    <!-- Add more repositories as needed -->
                </repositories>
            </configuration>
            <executions>
                <execution>
                    <id>git-to-maven-dependencies</id>
                    <goals>
                        <goal>git-to-maven-dependencies</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

```

You shouldn't need to change anything in this plugin other than the '<gitRepoUrl>' tags and the '<branch>' tags. These specify the github repos for the plugin to download and run 'mvn clean install' to install it in your project. 

This method allows you to easily incorporate a repo into your code as a dependency. However, it comes at a cost of speed, since the plugin has to spawn a process to 
run 'mvn clean install'. For a normal dependency, maven downloads a jar which is much faster than this method. 


