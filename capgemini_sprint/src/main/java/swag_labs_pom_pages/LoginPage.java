package swag_labs_pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	
	WebDriver driver;
	
	public  LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	
	
	@FindBy(id="user-name")
	private WebElement usernametf;
	
	@FindBy(id="password")
	private WebElement passwordtf;
	
	@FindBy(name="login-button")
	private WebElement loginbutton;
	
	@FindBy(xpath="//h3[@data-test=\"error\"]")
	private WebElement errormsg;
	
	@FindBy(xpath="//span[text()='Products']")
	private WebElement products;

	

	//getters
	
	public WebElement getUsernametf() {
		return usernametf;
	}

	public WebElement getPasswordtf() {
		return passwordtf;
	}

	public void getLoginbutton() {
		loginbutton.click();
	}
	
	public void getLogin(String un,String pwd)
	{
		getUsernametf().sendKeys(un);
		getPasswordtf().sendKeys(pwd);
		getLoginbutton();
	}
	
	public String printErrorMsg()
	{
		return errormsg.getText();
	}
	
	public void checkAlert()
	{
		Assert.assertTrue(errormsg.isDisplayed());
	}
	
	public void checkLogin()
	{
		WebElement product = driver.findElement(By.xpath("//span[text()='Products']"));
		Assert.assertTrue(product.isDisplayed());
		System.out.println("login successful");
	}
	
	public boolean isUsernameDisplayed()
	{
	    return usernametf.isDisplayed();
	}

	public boolean isPasswordDisplayed()
	{
	    return passwordtf.isDisplayed();
	}

	public boolean isLoginButtonDisplayed()
	{
	    return loginbutton.isDisplayed();
	}

	public boolean isUsernameEnabled()
	{
	    return usernametf.isEnabled();
	}

	public boolean isPasswordEnabled()
	{
	    return passwordtf.isEnabled();
	}

	public boolean isLoginButtonEnabled()
	{
	    return loginbutton.isEnabled();
	}

	public String getUsernameValue()
	{
	    return usernametf.getAttribute("value");
	}

	public String getPasswordValue()
	{
	    return passwordtf.getAttribute("value");
	}

	public String getPasswordType()
	{
	    return passwordtf.getAttribute("type");
	}

	public boolean isProductsDisplayed()
	{
	    return products.isDisplayed();
	}
}
