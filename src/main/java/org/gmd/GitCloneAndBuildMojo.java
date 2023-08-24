package org.gmd;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Mojo(name = "git-to-maven-dependencies")
public class GitCloneAndBuildMojo extends AbstractMojo {

    @Parameter(defaultValue = "https://github.com/yourusername/your-repo.git")
    private String gitRawLinkToJar;

    @Parameter(defaultValue = "${project.build.directory}/libs")
    private String targetDirectory;

    @Parameter(defaultValue = "library.jar")
    private String jarFilename;

    public GitCloneAndBuildMojo(String gitRawLinkToJar, String targetDirectory, String jarFilename) {
        this.gitRawLinkToJar = gitRawLinkToJar;
        this.targetDirectory = targetDirectory;
        this.jarFilename = jarFilename;
    }


    public void execute() {
        try {
            URL url = new URL(gitRawLinkToJar);
            Path targetPath = Path.of(targetDirectory, jarFilename);

            Files.createDirectories(targetPath.getParent());
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            getLog().info("JAR file downloaded successfully to " + targetDirectory);
        } catch (IOException e) {
            System.err.println("Error downloading the file: " + e.getMessage());
        }
    }
}
