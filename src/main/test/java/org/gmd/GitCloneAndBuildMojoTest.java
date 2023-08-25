package org.gmd;


public class GitCloneAndBuildMojoTest {


    public static void main(String args[]) {

        GitCloneAndBuildMojo mojo = new GitCloneAndBuildMojo(
            "https://github.com/wbf22/TestDependency/raw/main/src/main/java/org/test/TestDependency-1.0.0.jar",
            "/Users/brandon.fowler/Documents/UniversalUrl",
            "pg.java"
        );

        mojo.execute();


    }

}
