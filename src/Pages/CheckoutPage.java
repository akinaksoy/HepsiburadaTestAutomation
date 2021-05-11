package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class CheckoutPage extends BaseClass {
	
	WebDriverWait wait = new WebDriverWait(driver, 80);
	
	//public WebElement ProductList =  driver.findElements(By.xpath("//ul/li///div[1]/div/div[2]/div[2]//a"));
	public List<WebElement> AllChildrens = driver.findElements(By.xpath("//ul/li//div[1]/div/div[2]/div[2]//a"));

	
	
	
	public int selectedElementIndex;
	public WebElement product;
	public int NumberOfProductBeforeDeletion = AllChildrens.size();
	
	
	public By SepetimLabel = By.xpath("//h1[normalize-space()='Sepetim']");
	public By DeleteButton = By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/section/section/ul/li/div/div/div[2]/div[4]/div[2]/div/a[2]");
	public By DeleteMessagePopUp = By.xpath("//section/ul/li/div[2]/div[2]/span");
	
	
	//Validate Checkout Page elements
public void ValidatePage() {
		
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(SepetimLabel)));
		assertEquals(driver.findElement(SepetimLabel).getText(), "Sepetim");
		assertTrue(AllChildrens.size() >0,"There is no product on Checkout List");
		test.log(LogStatus.PASS,"Checkout page has been opened successfully");
		
	}


	// Delete to selected phone on checkout list
	public void DeletePhone(String Phone) {
		
		
		boolean correct = false; 
		int i = 0;
		for(WebElement product : AllChildrens) {
			i++;
			
			
			 // Find phone and delete it
			if(product.getText().equals(Phone)) {
				correct = true;
				// get element index for add to cart step
				driver.findElement(By.xpath("//ul/li["+i+"]/div/div/div[2]/div[4]/div[2]/div/a[2]")).click();
				test.log(LogStatus.PASS,"Phone has been deleted successfully");
				break;
			}else {
				correct = false;
				
			}
			
		}
		if(correct == false) test.log(LogStatus.FAIL,"Phone deletion is failed");
		assertEquals(correct, true,"Automation couldn't find product");
		
		
	}
	// Validate phone deletion on checkoutList
	public void ValidateDeletePopUp() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(DeleteMessagePopUp)));
		assertEquals(driver.findElement(DeleteMessagePopUp).getText(), "Ürün sepetinizden silinmiştir","Automation couldn't find <DeleteMessage>");
		test.log(LogStatus.PASS,"Phone has been deleted successfully");
		
	}

	
}
