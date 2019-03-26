package folder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties_util.LoadProperties;
import utils.FileUtil;

import java.io.File;

public class FileCleaner {

    private static final Logger LOGGER = LogManager.getLogger(FileCleaner.class.getName());

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
        LOGGER.info("Start cleaning ["+loadProperties.getProperties("file.path")+"] starting...");
        fileUtil.deleteFile(fileHolder.getFile());
        LOGGER.info("Cleaning ["+loadProperties.getProperties("file.path")+"] done");
    }
}
