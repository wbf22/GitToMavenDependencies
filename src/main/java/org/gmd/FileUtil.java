package org.gmd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static boolean deleteDirectory(Path directoryPath) {
        try {
            Files.walk(directoryPath)
                .sorted((p1, p2) -> -p1.compareTo(p2)) // Sort in reverse order to delete files before directories
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (Exception e) {
            return false;

        }
        return true;
    }
}
