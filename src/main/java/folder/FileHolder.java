package folder;

import java.io.File;
import java.nio.file.Path;

public class FileHolder {

    private File file;
    private Path path;

    public FileHolder(File filePath) {
        this.file = filePath;
    }

    public FileHolder(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public File getFile() {
        if (file.exists()) {
            return file;
        }else{
            return null;
        }
    }
}
