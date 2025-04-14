package Reports;

import com.Utils.commonUtility;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelResultWriter {

    private static Workbook workbook = new XSSFWorkbook();
    private static Sheet sheet = workbook.createSheet("FinalTestSheet_"+ commonUtility.getDateAndTime());
    private static int rowNum =0;

    static {
        Row header = sheet.createRow(rowNum++);
        header.createCell(0).setCellValue("Scenario Name");
        header.createCell(1).setCellValue("Status");
    }

    public static void writeResult(String scenarioName, String status){
        Row row =  sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(scenarioName);
        row.createCell(1).setCellValue(status);
    }

    public static void saveExcel(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
