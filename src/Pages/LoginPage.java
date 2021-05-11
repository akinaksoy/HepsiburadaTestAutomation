package Pages;

import static org.testng.Assert.assertTrue;


import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Utilites.GetDataFromExcel;

public class LoginPage extends BaseClass {

	WebDriverWait wait = new WebDriverWait(driver, 80);
	public By LoginPageScreen = By.xpath("//body/div[@id='root']/div/div/div/div/div[1]");
	public By EmailInput = By.id("txtUserName");
	public By PasswordInput = By.id("txtPassword");
	public By LoginButton = By.id("btnLogin");
	
	
	//Validate Login page items
	public void ValidateLoginPage() {
		assertTrue(driver.findElement(LoginPageScreen).isDisplayed(),"Login page is not loaded.");
		assertTrue(driver.findElement(EmailInput).isDisplayed(),"Automation couldn't find <EmailInput>");
		assertTrue(driver.findElement(PasswordInput).isDisplayed(), "Automation couldn't find <PasswordInput>");
		assertTrue(driver.findElement(LoginButton).isDisplayed(),"Automation couldn't find <LoginButton>");
		test.log(LogStatus.PASS,"Login page has been opened successfully");
	}
	//login with email and password
	public void Login() throws IOException {
		GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
		//get email and password from Excel
		String email =  getDataFromExcel.getEmail();
		String password = getDataFromExcel.getPassword();
		//-----------------------------------------------------------
		
		driver.findElement(EmailInput).sendKeys(email);
		driver.findElement(PasswordInput).sendKeys(password);
		driver.findElement(LoginButton).click();
		LayoutPage layoutPage = new LayoutPage();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(layoutPage.HepsiburadaLogo)));
		test.log(LogStatus.PASS,"Login process is successful");
	}
	
	
}
