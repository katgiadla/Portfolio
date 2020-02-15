package tchorek;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import tchorek.core.ExcelWriter;
import tchorek.core.PdfReader;
import tchorek.utils.Dictionary;
import tchorek.utils.FileNameReader;
import tchorek.utils.QuestionMapper;

@Log4j2()
public class PDFReader {

    private FileNameReader fileNameReader;
    private QuestionMapper questionMapper;
    private PdfReader pdfReader;
    private ExcelWriter excelWriter;

    public PDFReader() {
        fileNameReader = new FileNameReader();
        questionMapper = new QuestionMapper();
        pdfReader = null;
        excelWriter = null;
    }

    @SneakyThrows
    private void addFileName() {
        log.info("Proceeding pdf file");
        pdfReader = new PdfReader(Dictionary.getPathResourcesFolder() + fileNameReader.readFilename());
        log.info("Proceeding excel file");
        excelWriter = new ExcelWriter(Dictionary.getPathResourcesFolder() + fileNameReader.readFilename());
        fileNameReader.closeStream();
    }

    @SneakyThrows
    public void convertPDFToXLSX() {
        addFileName();
        excelWriter.saveToFile(questionMapper.extractQuestions(pdfReader.readPDF()));
        pdfReader.closeFile();
    }
}
