package tchorek.core;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;


public class PdfReader extends FileCreator {

    private PDDocument pdfFile;
    private PDFTextStripper pdfStripper;

    public PdfReader(String filePath) throws Exception {
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
