package Utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Pages.BaseClass;

public class WriteResultToExcel  {

	public static FileInputStream ExcelFile;
	public static XSSFWorkbook workbook;
	LocalDateTime now ;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
	
	public XSSFSheet openExcel() throws IOException {
		
		now = LocalDateTime.now();
		workbook = new XSSFWorkbook();
		XSSFSheet Sheet = workbook.createSheet("Test-"+dtf.format(now));
		
		return Sheet;
	}
	
	public void WriteExcel(XSSFSheet Sheet,ArrayList<String> MessageList) throws IOException {
		
		int testStep = 1;
		
		for(int i = 0 ; i < MessageList.size() ; i++) {
			Row row = Sheet.createRow(i);
			Cell cell = row.createCell(0);
			
			if(MessageList.get(i).contains("Test-"+testStep)) {
				cell.setCellValue(MessageList.get(i));
			}else {
				cell.setCellValue("Test-"+testStep+" is failed");
				i = i-1;
			}
			testStep = testStep+1;
			
			
			
			
			
		}
		write(workbook);
	}
	
	
	public void write(XSSFWorkbook workbook)
	 {
	  try 
	  {
	       FileOutputStream out = new FileOutputStream(new File("./Data/TestResult/TestResult.xlsx"));
	       workbook.write(out);
	       out.close();
	  } 
	  catch (FileNotFoundException e) {
	    e.printStackTrace();
	  } 
	  catch (IOException e) {
	       e.printStackTrace();
	  }
	 }
	
	
	
	
}
