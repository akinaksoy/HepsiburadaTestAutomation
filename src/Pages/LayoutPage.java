package Pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Utilites.GetDataFromExcel;

public class LayoutPage extends BaseClass {
	
	WebDriverWait wait = new WebDriverWait(driver, 80);
	public Actions action = new Actions(driver);
	
	
	
	public By HepsiburadaLogo = By.xpath("//a[@title='Hepsiburada']//*[local-name()='svg']");
	public By SearchInput = By.className("desktopOldAutosuggestTheme-input");
	public By SearchButton = By.className("SearchBoxOld-buttonContainer");
	public By ProfileBox = By.xpath("//div[@id='myAccount']");
	public By ProfileDropdown = By.className("sf-OldMyAccount-32BWo");
	public By LoginButton = By.id("login");
	public By MyFavouritesButton = By.xpath("//div[1]/div/div[2]/ul/li[5]/a");
	//Validate Hepsiburada.com LayoutElements
	public void ValidateLayout() {
		
		assertTrue(driver.findElement(HepsiburadaLogo).isDisplayed(), "Automation couldn't find <HepsiburadaLogo>");
		assertTrue(driver.findElement(SearchInput).isDisplayed(), "Automation couldn't find <SearchInput>");
		assertTrue(driver.findElement(SearchButton).isDisplayed(),"Automation couldn't find <SearchButton>");
		test.log(LogStatus.PASS,"Hepsiburada has been opened successfully.");
	}
	//Navigate to Login Page
	public void NavigateToLoginPage() {
		action.moveToElement(driver.findElement(ProfileBox)).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(LoginButton)));
		assertTrue(driver.findElement(LoginButton).isDisplayed(), "Automation couldn't find <LoginButton>");
		driver.findElement(LoginButton).click();
		test.log(LogStatus.PASS, "Login page has been opened successfully");
	}
	
	
	
	public void SearchPhone() throws IOException {
		// Get phone name from excel
		GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
		String PhoneName = getDataFromExcel.getPhoneName();
		
		driver.findElement(SearchInput).click();
		driver.findElement(SearchInput).sendKeys(PhoneName);
		driver.findElement(SearchButton).click();
		test.log(LogStatus.PASS, "Search progress is successful");
		
	}
	
	public void NavigateToFavouritesPage() {
		action.moveToElement(driver.findElement(ProfileBox)).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(MyFavouritesButton)));
		driver.findElement(MyFavouritesButton).click();
	}
	
	
	public void NavigateToCheckoutPage() {
		driver.navigate().to("https://checkout.hepsiburada.com/sepetim");
	}
	
	
	

}
