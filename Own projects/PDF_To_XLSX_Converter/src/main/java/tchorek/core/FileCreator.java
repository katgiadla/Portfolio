package tchorek.core;

import java.io.File;

abstract class FileCreator {

    protected File file;

    public FileCreator(String filePath) throws Exception {
        this.file = new File(filePath);
        if (filePath.contains(".pdf") && !file.isFile()) throw new Exception("File does not exist");
        if (filePath.contains(".xlsx") && file.isFile()) throw new Exception("File exists. Data override possibility");
    }
}
