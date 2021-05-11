package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Utilites.GetDataFromExcel;

public class SearchPage extends BaseClass {

	WebDriverWait wait = new WebDriverWait(driver, 80);
	
	
	public By SearchPageSection = By.xpath("//div[@class='row padding']");
	public By PhoneFilter = By.xpath("//label[normalize-space()='Telefon']");
	public By MobilePhoneFilter = By.xpath("//label[normalize-space()='Cep Telefonu']");
	public By CategoryTextSection = By.xpath("//span[@class='search-results-title']");
	public By PaginationSecond = By.xpath("//a[normalize-space()='2']");
	public By item5 = By.xpath("//li[5]//div[1]//a[1]");
	
	
	public void ValidateSearchPage() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(SearchPageSection)));
	}
	// Select phone and mobile phone filters. after that verify the page is samsung page.
	public void selectFilter() throws IOException {
		
		driver.findElement(PhoneFilter).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobilePhoneFilter)));
		driver.findElement(MobilePhoneFilter).click();
		test.log(LogStatus.PASS,"Mobile phone filter has been selected successfully");
		
		//get Data from Excel
		GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
		String phoneName = getDataFromExcel.getPhoneName();
		//------------------------------------------
		
		//Verify that the page is samsung page
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(CategoryTextSection)));
		assertTrue( driver.getTitle().contains(phoneName), "Automation is on the wrong page.This title doesn't contain <"+phoneName+">");
		String CategoryText = driver.findElement(CategoryTextSection).getText();
		assertEquals(CategoryText , "Cep Telefonu",CategoryText +" is not equals to <Telefon>");
		test.log(LogStatus.PASS,"The page has been verified by automation.");
		
		
		
		
	}
	
	// Navigate to second samsung page and verify this page is second page.
	public void NavigateToSecondPage() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(PaginationSecond)));
		driver.findElement(PaginationSecond).click();
		wait.until(ExpectedConditions.urlContains("sayfa=2"));
		test.log(LogStatus.PASS,"Second page has been opened successfully");
	}
	
	// select fifth product
	
	public void SelectFifthProduct() {
		driver.findElement(item5).click();
		test.log(LogStatus.PASS,"Fifth product has been opened");
		
	}
	
	
}
