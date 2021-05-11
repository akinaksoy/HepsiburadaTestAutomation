package Utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Pages.BaseClass;

public class GetDataFromExcel extends BaseClass {
		
	public static FileInputStream ExcelFile;
	
	public XSSFSheet openExcel() throws IOException {
		File file = new File("./Data/Variables/HepsiburadaDatas.xlsx");
		ExcelFile = new FileInputStream(file);
		XSSFWorkbook Datas = new XSSFWorkbook(ExcelFile);
		XSSFSheet Sheet = Datas.getSheetAt(0);
		
		return Sheet;
	}
	
	public void closeExcel() throws IOException {
		ExcelFile.close();
	}
	
	// Get email from Excel
	public String getEmail() throws IOException {
		XSSFSheet Sheet = openExcel();
		String email = Sheet.getRow(1).getCell(0).getStringCellValue();
		closeExcel();
		return email;
	}
	// Get password from Excel
	public String getPassword() throws IOException {
		XSSFSheet Sheet = openExcel();
		String password =  Sheet.getRow(1).getCell(1).getStringCellValue();
		closeExcel();
		return password;
	}
	// Get Phone name from Excel
	public String getPhoneName() throws IOException {
		XSSFSheet Sheet = openExcel();
		String phoneName =  Sheet.getRow(1).getCell(2).getStringCellValue();
		closeExcel();
		return phoneName;
	}
	
	
}
