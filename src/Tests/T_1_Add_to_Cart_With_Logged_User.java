package Tests;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BaseClass;
import Pages.CheckoutPage;
import Pages.LayoutPage;
import Pages.LoginPage;
import Pages.MyFavoritesPage;
import Pages.ProductDetailsPage;
import Pages.SearchPage;
import Utilites.WriteResultToExcel;

public class T_1_Add_to_Cart_With_Logged_User extends BaseClass{

	public String ProductDetailPhoneName;
	WriteResultToExcel writeResultToExcel = new WriteResultToExcel();
	
	
	@Test(priority = 1)
	public void Login() throws IOException {
		StartReport("Testcase-1 is failed");
		LayoutPage layoutPage = new LayoutPage();
		layoutPage.ValidateLayout();
		layoutPage.NavigateToLoginPage();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		LoginPage loginPage = new LoginPage();
		loginPage.ValidateLoginPage();
		loginPage.Login();
		
		TestResult.add("Test-1 Successfully done");
		EndReport("Test-1 Successfully done");
		
	}
	
	@Test(priority = 2 )
	public void SearchPhone() throws IOException {
		StartReport("Testcase-2 is failed");
		LayoutPage layoutPage = new LayoutPage();
		layoutPage.ValidateLayout();
		layoutPage.SearchPhone();
		TestResult.add("Test-2 Successfully done");
		
		EndReport("Test-2 Successfully done");
		
	}
	
	@Test(priority = 3)
	public void selectAndVerifyFilter() throws IOException {
		StartReport("Testcase-3 is failed");
		SearchPage searchPage = new SearchPage();
		searchPage.ValidateSearchPage();
		searchPage.selectFilter();
		TestResult.add("Test-3 Successfully done");
		EndReport("Test-3 Successfully done");
		
		
	}
	
	@Test(priority = 4)
	public void NavigateAndVerifySecondPage() {
		StartReport("Testcase-4 is failed");
		SearchPage searchPage = new SearchPage();
		searchPage.NavigateToSecondPage();
		TestResult.add("Test-4 Successfully done");
		EndReport("Test-4 Successfully done");
	}
	
	@Test(priority = 5)
	public void SelectFifthProduct() {
		StartReport("Testcase-5 is failed");
		SearchPage searchPage = new SearchPage();
		searchPage.SelectFifthProduct();
		ProductDetailsPage productDetailsPage = new ProductDetailsPage();
		productDetailsPage.ValidateProductDetailPage();
		ProductDetailPhoneName = productDetailsPage.getNameOfProduct();
		TestResult.add("Test-5 Successfully done");
		EndReport("Test-5 Successfully done");
		
		
	}
	
	@Test(priority=6)
	public void LikeProductAndVerifyPopUp() {
		StartReport("Testcase-6 is failed");
		ProductDetailsPage productDetailsPage = new ProductDetailsPage();
		productDetailsPage.LikeProduct();
		TestResult.add("Test-6 Successfully done");
		EndReport("Test-6 Successfully done");
	}
	
	@Test(priority=7)
	public void NavigateToMyFavoritesPage() {
		StartReport("Testcase-7 is failed");
		LayoutPage layoutPage = new LayoutPage();
		layoutPage.NavigateToFavouritesPage();
		MyFavoritesPage myFavoritesPage = new MyFavoritesPage();
		myFavoritesPage.ValidateMyFavoritesPage();
		TestResult.add("Test-7 Successfully done");
		EndReport("Test-7 Successfully done");
		
		
	}
	
	@Test(priority=8)
	public void AddtoCart() {
		StartReport("Testcase-8 is failed");
		MyFavoritesPage myFavoritesPage = new MyFavoritesPage();
		myFavoritesPage.ValidateAddedProduct(ProductDetailPhoneName);
		myFavoritesPage.addToCart();
		TestResult.add("Test-8 Successfully done");
		EndReport("Test-8 Successfully done");
		
	}
	
	@Test(priority=9)
	public void NavigateToCheckoutPage() {
		StartReport("Testcase-9 is failed");
		LayoutPage layoutPage = new LayoutPage();
		layoutPage.NavigateToCheckoutPage();
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.ValidatePage();
		TestResult.add("Test-9 Successfully done");
		EndReport("Test-9 Successfully done");
	}
	
	@Test(priority=10)
	public void DeleteProduct() {
		StartReport("Testcase-10 is failed");
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.DeletePhone(ProductDetailPhoneName);
		TestResult.add("Test-10 Successfully done");
		EndReport("Test-10 Successfully done");
		
	}
	@Test(priority = 11)
	public void ValidateDeleteProduct() {
		StartReport("Testcase-11 is failed");
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.ValidateDeletePopUp();
		TestResult.add("Test-11 Successfully done");
		EndReport("Test-11 Successfully done");

	}
	
	
	
	public void StartReport(String Message) {
		
		
		resultMessage = Message;
	}
	public void EndReport (String Message) {
		isPASS = true;
		resultMessage= Message;
	}
	
	
}
