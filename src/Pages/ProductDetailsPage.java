package Pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class ProductDetailsPage extends BaseClass {

	WebDriverWait wait = new WebDriverWait(driver, 80);
	
	public By ProductDetailPage = By.className("productDetailContent");
	public By LikeButton = By.xpath("//div[@id='MyLists']/div/div//div/div");
	public By LikePopUp = By.className("hb-toast-text");
	
	
	// Validate product detail page
	public void ValidateProductDetailPage() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(ProductDetailPage)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(LikeButton)));
		test.log(LogStatus.PASS,"Product detail page has been opened successfully");
		
		
	}
	// Like product on product details page
	public void LikeProduct() {
		driver.findElement(LikeButton).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(LikePopUp)));
		assertEquals(driver.findElement(LikePopUp).getText(), "Ürün listenize eklendi.","The popup that appears after clicking the like button is incorrect.");
		test.log(LogStatus.PASS,"Phone has been added to favorite list successfully");
	}
	
	// get name of product for using another testcases
	public String getNameOfProduct() {
		return driver.findElement(By.id("product-name")).getText();
	}
	
	
}
