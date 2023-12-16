import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DownloadsSort {
    public static void main(String[] args) {
        Path path = Path.of(System.getProperty("user.home"), "Downloads");
        if (Files.exists(path))
            System.out.println("Download directory path: " + path);
        else {
            System.out.println("Download directory not found");
            return;
        }
        File downloads = path.toFile();
        List<String> allFiles = new ArrayList<>(Arrays.asList(downloads.list()));
        Path currentFile;
        String ext="";
        int dot;
        for (String s : allFiles) {
            currentFile = Path.of(downloads.toString(), s);
            if(Files.isDirectory(currentFile))
                continue;
            dot = s.lastIndexOf('.');
            if(dot!=-1)
                ext = s.substring(dot+1);
            else
                continue;
            if(ext.equals("ini"))
                continue;
            try {
                if(!Files.exists(Path.of(System.getProperty("user.home"), "Downloads","_"+ext.toUpperCase())))
                    Files.createDirectory(Path.of(System.getProperty("user.home"), "Downloads","_"+ext.toUpperCase()));
                Files.move(currentFile,Path.of(System.getProperty("user.home"), "Downloads","_"+ext.toUpperCase(),s));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
