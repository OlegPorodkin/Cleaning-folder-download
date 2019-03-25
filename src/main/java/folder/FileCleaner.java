package folder;

import properties_util.LoadProperties;
import utils.FileUtil;

import java.io.File;

public class FileCleaner {

    private LoadProperties loadProperties;
    private FileHolder fileHolder;
    private FileUtil fileUtil;

    public FileCleaner() {
        exec();
    }

    private void exec() {
        loadProperties = new LoadProperties();

        fileHolder = new FileHolder(new File(loadProperties.getProperties("file.path")));

        fileUtil = new FileUtil(Integer.valueOf(loadProperties.getProperties("number.of.days")));
        fileUtil.deleteFile(fileHolder.getFile());
    }
}
