package swag_labs_pom_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverViewPage {

	WebDriver driver;
	
	public CheckoutOverViewPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(id="finish")
	private WebElement finishButton;
	
	@FindBy(name="cancel")
	private WebElement cancleButton;
	
	//getters
	
	public void clickFinishButton()
	{
		finishButton.click();
	}
	
	public void clickCancleButton()
	{
		cancleButton.click();
	}
	
	@FindBy(xpath="//span[text()='Checkout: Overview']")
	private WebElement checkoutOverviewText;

	public boolean isOverviewPageDisplayed()
	{
	    return checkoutOverviewText.isDisplayed();
	}
}
