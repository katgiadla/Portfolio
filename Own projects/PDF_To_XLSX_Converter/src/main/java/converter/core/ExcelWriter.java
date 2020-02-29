package converter.core;

import converter.utils.Dictionary.Questions;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.LinkedMultiValueMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Log4j2
public class ExcelWriter extends FileCreator {

    private Workbook workbook;
    private Sheet sheet;
    private Row row;
    private FileOutputStream outputStream;
    private int rowIndex, columnIndex;

    public ExcelWriter(String filePath) throws IOException {
        super(String.format("%s.xlsx", filePath));
        rowIndex = columnIndex = 0;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        String[] headers = new String[]{Questions.A.name(), Questions.B.name(), Questions.C.name(), Questions.QUESTION.name()};
        row = sheet.createRow(rowIndex++);
        writeHeaders(headers);
    }

    private void writeHeaders(String[] headers) {
        Arrays.stream(headers).forEach(header -> {
            sheet.setColumnWidth(columnIndex, 30 * 256);
            Cell cell = row.createCell(columnIndex++);
            cell.setCellValue(header);
        });
        columnIndex = 0;
    }

    public void saveToFile(LinkedMultiValueMap<String, String> questionAndAnswers) {
        try {
            writeDataToFile(questionAndAnswers);
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeDataToFile(LinkedMultiValueMap<String, String> questionsAndAnswers) {
        questionsAndAnswers.forEach((key, answers) -> {
            row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(answers.get(0));
            row.createCell(1).setCellValue(answers.get(1));
            row.createCell(2).setCellValue(answers.get(2));
            row.createCell(3).setCellValue(key);
        });
    }
}
