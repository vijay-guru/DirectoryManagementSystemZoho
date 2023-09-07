import java.util.ArrayList;
import java.util.List;

public class Directory {
    String directoryName;
    Directory previousDirectory;
    List<Directory> subDirectories;
    List<File>files;

    public Directory(String directoryName, Directory previousDirectory) {
        this.directoryName = directoryName;
        this.previousDirectory = previousDirectory;
        this.subDirectories = new ArrayList<Directory>();
        this.files = new ArrayList<File>();
    }

}
