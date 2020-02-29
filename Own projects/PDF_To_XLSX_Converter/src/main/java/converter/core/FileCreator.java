package converter.core;

import converter.utils.Dictionary;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
@Log4j2
abstract class FileCreator {

    protected File file;

    FileCreator(String filePath) throws IOException {
        this.file = new File(filePath);
        if (filePath.contains(".pdf") && !file.isFile())
            throw new IOException("File does not exist");
        if (filePath.contains(".xlsx") && file.isFile())
            throw new IOException("File exists. Data override possibility");
    }

    protected void removeFile(String filename){
        File fileToDelete = new File(Dictionary.getPathResourcesFolder()+filename);
        if(fileToDelete.delete())
            log.debug(String.format("File %s deleted", fileToDelete.getName()));
        else
            log.error(String.format(" source: %s", fileToDelete.getName()));
    }
}
