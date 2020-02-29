package converter.core;

import converter.utils.Dictionary;
import converter.utils.FileNameReader;
import converter.utils.QuestionMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileConventer {

    private QuestionMapper questionMapper;
    private PDFReader PDFReader;
    private ExcelWriter excelWriter;

    public FileConventer() {
        questionMapper = new QuestionMapper();
        PDFReader = null;
        excelWriter = null;
    }

    private void addFileNames(String pdfName, String xlsxName) throws Exception {
        log.info("Write pdf file name");
        PDFReader = new PDFReader(Dictionary.getPathResourcesFolder() + pdfName);
        log.info("Write excel output file name");
        excelWriter = new ExcelWriter(Dictionary.getPathResourcesFolder() + xlsxName);
    }

    public void convertPDFToXLSX(String pdfName, String xlsxName) throws Exception {
        addFileNames(pdfName, xlsxName);
        excelWriter.saveToFile(questionMapper.extractQuestions(PDFReader.readPDF()));
    }

    public void removeXLSXFile(String fileName){
        excelWriter.removeFile(fileName);
    }
}
