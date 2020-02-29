package converter.core;

import converter.utils.Dictionary;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.io.IOException;

import static com.sun.javafx.font.FontResource.ZERO;

@CucumberOptions(glue = {"converter.core"},
        features = "src/test/java/converter/features/")
public class PDFReaderTest {

    private final StringBuilder pdfContent;
    private String fileName;
    private Exception actualException;
    private PDFReader loadedPDFFile;

    public PDFReaderTest() {
        pdfContent = new StringBuilder();
    }

    @Given("^One PDF file$")
    public void initializePDFFile() throws Exception {
        fileName = "PytaniaABC";
    }

    @When("^Service tries to get its content$")
    public void readPDF() throws IOException {
        try {
            loadedPDFFile = new PDFReader(Dictionary.getPathResourcesFolder() + fileName);
            pdfContent.append(loadedPDFFile.readPDF());
        } catch (IOException e) {
            actualException = e;
        }
    }

    @Then("^Service has file content$")
    public void checkReadingStatus() {
        Assert.assertTrue(pdfContent.toString().length() > 10);
        clearPDFContener();
    }

    @And("^No exception related with PDF should be thrown$")
    public void noExceptionShouldBeThrown() throws Throwable {
        Assert.assertNull(actualException);
    }

    @Given("^No PDF file$")
    public void initializeNotExistingPDFFile() {
        clearPDFContener();
        fileName = "Unknown";
    }

    @Then("^Exception related with PDF should be thrown$")
    public void handleNonExistingPDFFile() {
        Assert.assertEquals("IOException", actualException.getClass().getSimpleName());
        clearPDFContener();
    }

    private void clearPDFContener() {
        pdfContent.setLength(ZERO);
    }
}
