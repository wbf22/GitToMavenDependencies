package org.gmd;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;


@Mojo(name = "git-to-maven-dependencies")
public class GitCloneAndBuildMojo extends AbstractMojo {

    @Parameter(defaultValue = "https://github.com/yourusername/your-repo.git")
    private String gitRepoUrl;

    @Parameter(defaultValue = "${project.build.directory}/git-repo")
    private String targetDirectory;

    @Parameter(defaultValue = "main")
    private String branch;

    public GitCloneAndBuildMojo(String gitRepoUrl, String targetDirectory, String branch) {
        this.gitRepoUrl = gitRepoUrl;
        this.targetDirectory = targetDirectory;
        this.branch = branch;
    }

    public void execute() {
        try {
            // Clone the Git repository
            CloneCommand cloneCommand = Git.cloneRepository()
                .setBranch(branch)
                .setURI(gitRepoUrl)
                .setDirectory(new File(targetDirectory));
            Git git = cloneCommand.call();

            // Change to the cloned directory
            File clonedDirectory = git.getRepository().getDirectory();
            clonedDirectory = clonedDirectory.getParentFile(); // Remove .git from the path

            // Run 'mvn clean install'
            ProcessBuilder processBuilder = new ProcessBuilder("mvn", "clean", "install");
            processBuilder.directory(clonedDirectory);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                getLog().info("Maven build successful.");
            } else {
                getLog().error("Maven build failed.");
            }
            FileUtil.deleteDirectory(clonedDirectory.toPath());

        } catch (GitAPIException | InterruptedException | IOException e) {
            getLog().error("Error during Git clone or Maven build.", e);
        }
    }
}
