package swag_labs_pom_pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
	WebDriver driver;
	 
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	//product sort dropdown
	
	@FindBy(xpath="//select[@class=\"product_sort_container\"]")
	private WebElement productSortdd;
	
	//sort options
	@FindBy(xpath="//select[@data-test=\"product-sort-container\"]")
	private WebElement sortoptions;
	
	//one option
	@FindBy(xpath="//option[text()='Name (Z to A)']")
	private WebElement option;
		
		
	//hamburger menu 
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement menuButton;
	
	//shopping cart icon 
	
	@FindBy(id="shopping_cart_container")
	private WebElement cartIcon;

	//number of items in cart
	
	@FindBy(xpath="//span[@class=\"shopping_cart_badge\"]")
	private WebElement itemCountInCart;
	
	
	
	
	//getters
	
	

	//hamburger menu 
	
	public void clickMenubutton() {
		menuButton.click();
	}


	//shopping cart icon 
	public void clickCarticon() {
		cartIcon.click();
	}
	
	
	//  getting / returning the count of number of items in cart
	
	public String getNoofcartitems()
	{
		return itemCountInCart.getText();
	}
	
	//selecting a product
	
	public void addProductToCartDirectly(String ProductName) 
	{
		WebElement product=driver.findElement(By.xpath("//div[@data-test=\"inventory-item\"][.//div[text()='"+ProductName+"']]"));
		product.findElement(By.tagName("button")).click();
	}

	//clicking a product  to see product details
	
	public void selectProduct(String ProductName)
	{
		WebElement product=driver.findElement(By.xpath("//div[@data-test=\"inventory-item\"][.//div[text()='"+ProductName+"']]"));
		product.findElement(By.xpath("//div[@data-test=\"inventory-item-name\"]")).click();
	}
	
	
	//remove product from cart in home page 
	
	public void removeProductFromCart(String ProductName)
	{
		WebElement product=driver.findElement(By.xpath("//div[@data-test=\"inventory-item\"][.//div[text()='"+ProductName+"']]"));
		product.findElement(By.tagName("button")).click();
		
	}
	
	//product sort dropdown 
		public void clickProductsortdd() {
			productSortdd.click();
		}
		
	
	//sorting the products using the options in dropdown
	
	public void sortProducts(String options)
	{
		 Select option=new Select(sortoptions); //select class is used
		 option.selectByVisibleText(options);
	}
	
	//Verification
	public boolean isOptionDisplayed()
	{
		return option.isDisplayed();
	}
	
	
	
	
	public List<String> getProductNames()
	{
	    List<WebElement> products = driver.findElements(By.xpath("//div[@data-test='inventory-item-name']"));
	    List<String> names = new ArrayList<>();
	    for(WebElement product : products)
	    {
	        names.add(product.getText());
	    }
	    return names;
	}
	
	
	
	
	public List<Double> getProductPrices()
	{
	    List<WebElement> prices = driver.findElements(By.xpath("//div[@data-test='inventory-item-price']"));
	    List<Double> priceList = new ArrayList<>();
	    for(WebElement price : prices)
	    {
	        String priceText = price.getText().replace("$", "");
	        priceList.add(Double.parseDouble(priceText));
	    }
	    return priceList;
	}
	
	
	public boolean isProductSortDisplayed()
	{
	    return productSortdd.isDisplayed();
	}
	
	public String getProductButtonText(String productName)
	{
	    WebElement product = driver.findElement( By.xpath("//div[@data-test='inventory-item'][.//div[text()='" + productName + "']]"));

	    return product.findElement(By.tagName("button")).getText();
	}
	
}
