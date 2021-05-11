package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class MyFavoritesPage extends BaseClass {

	WebDriverWait wait = new WebDriverWait(driver, 80);
	public WebElement product;
	public int selectedElementIndex ;
	public Actions action = new Actions(driver);
	
	public By MyFavoritesSection = By.xpath("//body/div[@id='root']/div[@class='main']/div[@class='myList-main']/div[2]");
	public By productAddedPopUp = By.xpath("//div[@class='hb-toast-text']");
	public WebElement ProductList =  driver.findElement(By.xpath("//div[@class='product-list']"));
	public List<WebElement> AllChildrens = ProductList.findElements(By.xpath("div"));
	
	// Validate Myfavorites page
	public void ValidateMyFavoritesPage() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(MyFavoritesSection)));
		assertTrue(AllChildrens.size() >0,"There is no product on favorites List");
		test.log(LogStatus.PASS,"My Favorites page has been opened successfully");
	}
	
	
	//validate added product on previous page
	public void ValidateAddedProduct(String Phone) {
		assertTrue(AllChildrens.size() >0);
		System.out.println(AllChildrens.size());
		boolean correct = false; 
		//Find phone on My Favorites page
		for(int i = 0 ; i <= AllChildrens.size() ; i++) {
			 product = AllChildrens.get(i).findElement(By.xpath("//a/img"));
			 
			if(product.getAttribute("alt").equals(Phone)) {
				correct = true;
				// get element index for add to cart step
				selectedElementIndex = i ; 
				test.log(LogStatus.PASS,"Phone has been found successfully");
				break;
			}else {
				correct = false;
				test.log(LogStatus.FAIL,"Automation couldn't find phone");
			}
			
		}
		
		assertEquals(correct, true);
		
	
	
	}
	// add selected product to cart
	public void addToCart() {
		
			
			action.moveToElement(product).perform();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			product = AllChildrens.get(selectedElementIndex).findElement(By.xpath("//span[contains(text(),'Sepete Ekle')]"));
			product.click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(productAddedPopUp)));
			assertEquals(driver.findElement(productAddedPopUp).getText(), "Ürün sepete eklendi");
			test.log(LogStatus.PASS,"Phone has been added to Cart successfully");
		
	}
	
	
	
	
}
