package swag_labs_pom_pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(id="continue-shopping")
	private WebElement continueShoppingButton;
	
	@FindBy(name="checkout")
	private WebElement checkoutButton;
	
	//getters
	
	public void clickContinueShopping()
	{
		continueShoppingButton.click();
	}
	
	public void clickCheckoutButton()
	{
		checkoutButton.click();
	}

	public void removeProFromCart(String productName)
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement product = wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath("//div[@data-test='inventory-item'][.//div[text()='" + productName + "']]")) );

	    product.findElement(By.tagName("button")).click();
	}
	
	
	public boolean isProductDisplayed(String productName) {
	    return !driver.findElements(
	        By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")).isEmpty();
	}
	
	public boolean isProductRemoved(String ProductName)
	{
		
	    return driver.findElements(By.xpath("//div[@data-test=\"inventory-item\"][.//div[text()='"+ProductName+"']]")).isEmpty();
	}

}
