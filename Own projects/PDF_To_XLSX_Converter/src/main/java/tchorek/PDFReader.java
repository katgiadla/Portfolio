package tchorek;

import tchorek.utils.Dictionary;
import tchorek.core.ExcelWriter;
import tchorek.core.PdfReader;
import tchorek.utils.QuestionMapper;

public class PDFReader {
    public static void main(String[] args) {
        try {
            PdfReader pdfReader = new PdfReader(Dictionary.getPath() + Dictionary.getSourceFileName());
            ExcelWriter excelWriter = new ExcelWriter(Dictionary.getPath() + Dictionary.getTargetFileName());
            QuestionMapper questionMapper = new QuestionMapper();
            excelWriter.saveToFile(questionMapper.extractQuestions(pdfReader.readPDF()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
        Todo: Gui allows user to change target pdf name and csv target name
     */
}
