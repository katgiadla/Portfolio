package converter;

import converter.core.FileConventer;
import converter.utils.FileNameReader;

public class Main {
    private static FileNameReader fileNameReader;

    public static void main(String[] args) throws Exception {
        FileConventer fileConventer = new FileConventer();
        fileConventer.convertPDFToXLSX(fileNameReader.readFilename(), fileNameReader.readFilename());
        fileNameReader.closeStream();
    }
}
