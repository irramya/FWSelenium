package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import fwconstants.FilePath;

public class XLOps {

	private static Workbook workbook;
	private static Sheet sheet;
	private static Row row;
	private static Properties prop;

	public static void loadXL(String excelFile, String sheetName) throws EncryptedDocumentException, FileNotFoundException, IOException {
		workbook = WorkbookFactory.create(new FileInputStream(FilePath.excelPath+new File(excelFile)));
		sheet = workbook.getSheet(sheetName);
	}
	
	public static String readXL(int rownum, int cellnum) {
		row = sheet.getRow(rownum);
		return row.getCell(cellnum).getStringCellValue();
	}
	
	public static void writeXL(int rownum, int cellnum, String value) {
		row = sheet.getRow(rownum);
		row.getCell(cellnum).setCellValue(value);
	}
	
	public static int getLastRow() {
		return sheet.getLastRowNum();
	}
	
	public static int getLastCell(int rownum) {
		row = sheet.getRow(rownum);
		return row.getLastCellNum();
	}
	
	public static LinkedHashMap<String, String> getTestInfo(String testcaseName) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
		int rows = sheet.getPhysicalNumberOfRows();
		for(int row=2; row<rows; row++) {
			if(sheet.getRow(row).getCell(0).getStringCellValue().equals(testcaseName)) {
				lhm.put("HttpMethod", sheet.getRow(row).getCell(1).getStringCellValue());
				lhm.put("EndPoint", sheet.getRow(row).getCell(2).getStringCellValue());
				lhm.put("ReqBody", sheet.getRow(row).getCell(5).getStringCellValue());
				lhm.put("StatusCode", sheet.getRow(row).getCell(6).getStringCellValue());
				lhm.put("ResponseTime", sheet.getRow(row).getCell(8).getStringCellValue());
			}
		}
		return lhm;
	}


}
