package converter.core;

import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;

@Log4j2
public class PDFReader extends FileCreator {

    private PDDocument pdfFile;
    private PDFTextStripper pdfStripper;

    public PDFReader(String filePath) throws IOException {
        super(String.format("%s.pdf", filePath));
        pdfStripper = new PDFTextStripper();
        pdfFile = PDDocument.load(file);
    }

    public void setReadScope(int pageStart, int pageEnd) {
        pdfStripper.setStartPage(pageStart);
        pdfStripper.setEndPage(pageEnd);
    }

    public String readPDF() throws IOException {
        return pdfStripper.getText(pdfFile);
    }
}
