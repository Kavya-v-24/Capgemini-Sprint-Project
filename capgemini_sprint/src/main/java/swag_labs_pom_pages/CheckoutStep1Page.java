package swag_labs_pom_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStep1Page {
	
	WebDriver driver;
	 public CheckoutStep1Page(WebDriver driver)
	 {
		 this.driver=driver;
			PageFactory.initElements( driver,this);
	 }
	 
	 @FindBy(xpath="//input[@placeholder=\"First Name\"]")
	 private WebElement firstnametf;
	 
	 @FindBy(id="last-name")
	 private WebElement lastnametf;
	 
	 @FindBy(name="postalCode")
	 private WebElement postalcodetf;
	 
	 @FindBy(id="continue")
	 private WebElement continueButton;
	 
	 @FindBy(xpath="//button[text()='Cancel']")
	 private WebElement cancleButton;
	 
	 
	 //getters
	 
	 public WebElement getFirstnametf() {
		 return firstnametf;
	 }

	 public WebElement getLastnametf() {
		 return lastnametf;
	 }

	 public WebElement getPostalcodetf() {
		 return postalcodetf;
	 }
	 
	 public void clickContinueButton() {
		continueButton.click();
	}
	  
	 
	public void clickCancleButton()
	{
		cancleButton.click();
	}
	 
	 

	 public void checkoutinformation(String fn,String ln,String pc)
	 {
		 getFirstnametf().sendKeys(fn);
		 getLastnametf().sendKeys(ln);
		 getPostalcodetf().sendKeys(pc);
		 
	 }
	 
	 @FindBy(xpath="//h3[@data-test='error']")
	 private WebElement errorMessage;

	 public boolean isErrorMessageDisplayed()
	 {
	     return errorMessage.isDisplayed();
	 }

}
