package org.gmd;


public class GitCloneAndBuildMojoTest {


    public static void main(String args[]) {

        GitCloneAndBuildMojo mojo = new GitCloneAndBuildMojo(
            "https://raw.githubusercontent.com/wbf22/PickGraph/main/src/main/java/org/pickgraph/PickGraph.java",
            "/Users/brandon.fowler/Documents/UniversalUrl",
            "pg.java"
        );

        mojo.execute();


    }

}
