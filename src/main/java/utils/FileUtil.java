package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class FileUtil {

    private static final Logger LOGGER = LogManager.getLogger(FileUtil.class.getName());

    private final int numberOfDays;

    public FileUtil(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public boolean deleteFile(final File file) {

        assert file != null;

        if (((getTime() - file.lastModified()) / (1000 * 60 * 60 * 24)) > numberOfDays) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteFile(new File(file, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
            if (!file.getName().equals("Download")) {
                LOGGER.info("delete" + file.getName());
                return file.delete();
            } else return true;
        } else {
            return false;
        }
    }

    public void deleteFile(final Path path) throws IOException {

        if ((getTime() - path.toFile().lastModified()) / (1000 * 60 * 60 * 24) > numberOfDays) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    LOGGER.info("delete" + file.getFileName());
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

                    if (exc != null) {
                        throw exc;
                    }
                    if (!dir.getFileName().toString().equals("Download")) {
                        LOGGER.info("delete" + dir.getFileName());
                        Files.delete(dir);
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    private static Long getTime() {
        return new Date().getTime();
    }
}
