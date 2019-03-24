package folder;

import utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileCleaner {

    public FileCleaner() {
        exec();
    }

    private void exec() {
        try {

            Properties properties = new Properties();
            InputStream is = FileCleaner.class.getResourceAsStream("/resources.properties");
            properties.load(is);

            File file = new File(properties.getProperty("file.path"));

            FileHolder fileHolder = new FileHolder(file);

            FileUtil fileUtil = new FileUtil(Integer.valueOf(properties.getProperty("number.of.days")));
            fileUtil.deleteFile(fileHolder.getFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
