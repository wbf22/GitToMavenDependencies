package org.gmd;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class GitCloneAndBuildMojoTest {


    public static void main(String args[]) {

//        GitCloneAndBuildMojo mojo = new GitCloneAndBuildMojo(
//            "https://github.com/wbf22/PickGraph.git",
//            "/Users/brandon.fowler/Documents/UniversalUrl/git-repo",
//            "main"
//        );
//
//        mojo.execute();
        String urlPath = "https://raw.githubusercontent.com/wbf22/PickGraph/main/pom.xml";

        try {
            URL url = new URL(urlPath);
            Path targetPath = Path.of("/Users/brandon.fowler/Documents/UniversalUrl/git-repo");

            // Download the file and save it to the target path
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded successfully.");
        } catch (IOException e) {
            System.err.println("Error downloading the file: " + e.getMessage());
        }

    }

}
