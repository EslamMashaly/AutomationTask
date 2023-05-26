package TestDataFiles;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import settings.TestSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    static FileInputStream fis = null;

    public FileInputStream getFileInputStream(){
        String filepath= TestSettings.RegistrationDataExcelFile;
        File srcFile= new File (filepath);
        try {
            fis=new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Check file path of test data");
            System.exit(0);
        }

        return fis;
    }



    public Object [][] getExcelData() throws IOException {
        fis=getFileInputStream();
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        int totalNumberOfRows=(sheet.getLastRowNum()+1);
        int totalNumberOfCols=6;

        String [][] arrayOfExcelData= new String[totalNumberOfRows][totalNumberOfCols];

        for (int i = 0; i < totalNumberOfRows; i++) {
            for (int j = 0; j <totalNumberOfCols ; j++) {
                XSSFRow row=sheet.getRow(i);
                arrayOfExcelData[i][j]=row.getCell(j).toString();
            }
        }
        wb.close();
        return arrayOfExcelData;
    }
}
