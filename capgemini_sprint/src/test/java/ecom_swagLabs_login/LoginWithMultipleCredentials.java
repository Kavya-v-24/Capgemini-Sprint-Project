package ecom_swagLabs_login;

import org.testng.Reporter;
import org.testng.annotations.Test;



import baseclass_utility.LoginBaseClass;
import swag_labs_pom_pages.LoginPage;
import swaglabs_exceldata.LoginMultipleCredentialsData;

public class LoginWithMultipleCredentials extends LoginBaseClass {

	@Test(dataProvider = "validLogin", dataProviderClass = LoginMultipleCredentialsData.class)

	public void validLoginTest(String un, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.getLogin(un, pwd);
		lp.checkLogin();
		Reporter.log("Login successful with valid credentials",true);
		Thread.sleep(2000);
	}

	@Test(dataProvider = "invalidLogin", dataProviderClass = LoginMultipleCredentialsData.class)
	public void invalidLoginTest(String un, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.getLogin(un, pwd);
		lp.checkAlert();
		Reporter.log(lp.printErrorMsg(),true);
		Reporter.log("Login failed with Invalid credentials",true);

		Thread.sleep(2000);

	}

}
