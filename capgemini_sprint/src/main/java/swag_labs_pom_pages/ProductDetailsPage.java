package swag_labs_pom_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    WebDriver driver;
    public ProductDetailsPage(WebDriver driver)
    {
    	this.driver=driver;
    	PageFactory.initElements( driver,this);
    }
    
    @FindBy(id="add-to-cart")
    private WebElement addToCartButton;
    
    @FindBy(id="remove")
    private WebElement removeButton;
    
    public void clickAddtoCartButton()
    {
    	addToCartButton.click();
    }
    
    public void clickRemoveButton()
    {
    	removeButton.click();
    }
    
}
