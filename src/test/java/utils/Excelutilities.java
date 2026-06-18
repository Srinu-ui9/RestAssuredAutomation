package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelutilities {

	public static String DataFetchFromExcel(String sheetName, int rowValue, int colValue) throws Exception {

		String path = System.getProperty("user.dir");
		String excelPath = path + "/src/test/resources/testdata.xlsx";

		FileInputStream file = new FileInputStream(excelPath); 
		Workbook workbook = WorkbookFactory.create(file); 
		Sheet sheet = workbook.getSheet(sheetName); 

		if (sheet == null) {
			throw new RuntimeException("Sheet not found: " + sheetName);
		}

		String data = sheet.getRow(rowValue).getCell(colValue).toString();

		workbook.close();
		file.close();

		return data;
	}

	public static int getRowCount(String sheetName) throws Exception {

		String path = System.getProperty("user.dir");
		String excelPath = path + "/src/test/resources/testdata.xlsx";

		FileInputStream file = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();

		workbook.close();
		file.close();

		return rows;
	}
}