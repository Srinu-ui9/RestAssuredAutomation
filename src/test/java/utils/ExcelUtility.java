package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	private Workbook workbook;
	private Sheet sheet;

	// Open Excel workbook
	public void openWorkbook(String filePath) throws IOException {

		FileInputStream file = new FileInputStream(filePath);

		workbook = WorkbookFactory.create(file);
	}

	// Select Excel sheet
	public void selectSheet(String sheetName) {

		sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
            throw new IllegalArgumentException(
                    "Sheet not found: " + sheetName
            );
        }
	}

	// Get cell data
	public String getCellData(int rowNumber, int columnNumber) {

		Row row = sheet.getRow(rowNumber);

		if (row == null) {
			return "";
		}

		Cell cell = row.getCell(columnNumber);

		if (cell == null) {
			return "";
		}

		DataFormatter formatter = new DataFormatter();

		return formatter.formatCellValue(cell);
	}

	// Get total number of rows
	public int getRowCount() {

		return sheet.getLastRowNum();
	}

	// Get total number of columns
	public int getColumnCount() {

		Row row = sheet.getRow(0);

		if (row == null) {
			return 0;
		}

		return row.getLastCellNum();
	}

	// Close Excel workbook
	public void closeWorkbook() throws IOException {

		if (workbook != null) {
			workbook.close();
		}
	}
}
