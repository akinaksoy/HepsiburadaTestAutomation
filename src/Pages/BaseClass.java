package Pages;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilites.WriteResultToExcel;

public class BaseClass {
	//ExtentReport variables
	public static boolean isPASS = false; 
	public static ExtentReports extent ;
	public static ExtentTest test;
	public static String testcaseName ; 
	public static String resultMessage="";
	
	//webDriver
	public static WebDriver driver;
	
	//EXCEL variables
	WriteResultToExcel writeResultToExcel = new WriteResultToExcel();
	XSSFSheet Sheet; 
	public static ArrayList<String> TestResult = new ArrayList<String>();
	WriteResultToExcel writeToExcel = new WriteResultToExcel();
	
	@BeforeSuite
	public void openBrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver", "./Data/ChromeDriver/chromedriver.exe");
		
		Sheet = writeResultToExcel.openExcel();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hepsiburada.com/");
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		extent = new ExtentReports("./Data/TestResult/ExtentReport.html",true);
		test = extent.startTest("T-1 Add To Cart With Logged User");
		
	}
	
	@AfterSuite
	public void closeBrowser() throws IOException {
		writeToExcel.WriteExcel(Sheet, TestResult);
		
		driver.close();
		extent.endTest(test);
		extent.flush();
		
	}
	
	
	@BeforeMethod
	public void StartTest() {
		
		isPASS = false;
		
		
	}
	@AfterMethod
	public void EndTest() {
		
		if (isPASS == true) {
				test.log(LogStatus.PASS,resultMessage);
				
		        
		        
		}else {
			test.log(LogStatus.FAIL,resultMessage);
				
				
	       
		}
		
		
		
	}
	

	
}
