package converter.core;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.sun.javafx.font.FontResource.ZERO;

@CucumberOptions(
        format={"pretty"},
        glue = {"converter.core"},
        features = "src/test/java/converter/features/")
public class PDFToXLSXTest {

    private final StringBuilder pdfContent;
    private String xlsxName;
    private String pdfName;
    private Exception actualException;
    private FileConventer fileConventer;

    public PDFToXLSXTest() {
        pdfContent = new StringBuilder();
    }

    @Before
    public void prepareXLSXFile() throws Exception {
        fileConventer = new FileConventer();
        pdfName = "PytaniaABC";
        xlsxName = "Results";
        fileConventer.convertPDFToXLSX(pdfName, xlsxName);
    }

    @After
    public void removeTestFiles(){
        fileConventer.removeXLSXFile("NewFile.xlsx");
        fileConventer.removeXLSXFile("Results.xlsx");
    }

    @Given("^PDF file only in the folder$")
    public void noFileWithSpecificNameInFolder(){
        xlsxName = "NewFile";
    }

    @When("^Service tries to save content to XLSX file$")
    public void serviceTriesToSaveItsPreparedContent() {
        try {
            fileConventer.convertPDFToXLSX(pdfName, xlsxName);
        } catch (Exception e) {
            actualException = e;
        }
    }

    @Then("^No exception related with that process should be thrown$")
    public void fileHasBeenCreated(){
        Assert.assertNull(actualException);
    }


    @Given("^PDF and XLSX files in the folder$")
    public void fileWithSpecificNameInFolder(){
        clearPDFContener();
        xlsxName = "Results";
    }

    @Then("^Exception related with existing xlsx should be thrown$")
    public void handleNonExistingPDFFile() {
        Assert.assertEquals("IOException", actualException.getClass().getSimpleName());
        clearPDFContener();
    }

    private void clearPDFContener() {
        pdfContent.setLength(ZERO);
    }
}
