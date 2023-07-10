package eCommerce.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesReader {

    public static String processFiles(String filePath){
        try {
            String fileToString = new String(Files.readAllBytes(Paths.get(filePath)));
            return fileToString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
