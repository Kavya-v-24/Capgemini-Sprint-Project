package swag_labs_pom_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
	
	WebDriver driver;
	
	public CheckoutCompletePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(id="back-to-products")
	private WebElement backToProButton;
	
	@FindBy(id="generate-pdf-order")
	private WebElement generatepdfButton;
	
	//getters
	
	public void clickBackToProduct()
	{
		backToProButton.click();
	}
	
	public void clickgeneratepdf()
	{
		generatepdfButton.click();
	}	
	
	@FindBy(xpath="//h2[text()='Thank you for your order!']")
	private WebElement orderConfirmation;

	public boolean isOrderConfirmationDisplayed()
	{
	    return orderConfirmation.isDisplayed();
	}

}
