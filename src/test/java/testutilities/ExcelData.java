package testutilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	String fileName ="./src/test/resources/testdata/testdata.xlsx";
	
	private String readTheFileData(int a, int b) throws IOException
	{
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("UserData");
		//XSSFSheet sheet = wb.getSheet("SITData");
		//int row = sheet.getLastRowNum();
		//System.out.println(row);
		//for(int i=0;i<=row;i++) {
			XSSFRow r = sheet.getRow(a);
			System.out.println(r.getCell(b));
			//System.out.println(r.getCell(b));
			XSSFCell aa = r.getCell(b);
			String data = aa.getStringCellValue();
		//}
		
		return data;
	}
	private String readTheFileData(String sheetName, int a, int b) throws IOException
	{
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow r = sheet.getRow(a);
		System.out.println(r.getCell(b));
		//System.out.println(r.getCell(b));
		XSSFCell aa = r.getCell(b);
		String data = aa.getStringCellValue();
		//}
		
		return data;
	}
	public String readTheDataCol(int row, int col) throws IOException
	{
		
		return readTheFileData(row, col);
	}
	public String readTheDataCol(String sheetName, int row, int col) throws IOException
	{
		
		return readTheFileData(sheetName, row, col);
	}
	
}
