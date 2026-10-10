package ecom_swagLabs_login;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass_utility.LoginBaseClass;
import generic_utility.ScreenshotUtility;
import swaglabs_exceldata.ExcelTestData;
import swag_labs_pom_pages.LoginPage;

public class LoginModule extends LoginBaseClass {

	// TC-LOGIN-01
	// Functionality
	// Verify Username and Password fields accept entered credentials

	@Test(groups = { "Functionality", "Smoke" })
	public void TC_LOGIN_01() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String username = data.getExceldata("validLoginData", 1, 0);

		String password = data.getExceldata("validLoginData", 1, 1);

		lp.getUsernametf().sendKeys(username);
		lp.getPasswordtf().sendKeys(password);

		Assert.assertEquals(lp.getUsernameValue(), username, "Username was not entered correctly");

		Assert.assertEquals(lp.getPasswordValue(), password, "Password was not entered correctly");
		
		Reporter.log("TC_LOGIN_01: Username and Password fields accept entered credentials",true);
	}

	// TC-LOGIN-02
	// Functionality
	// Verify Password field masks the entered password

	@Test(groups = "Functionality")
	public void TC_LOGIN_02() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String password = data.getExceldata("validLoginData", 1, 1);

		lp.getPasswordtf().sendKeys(password);

		Assert.assertEquals(lp.getPasswordType(), "password", "Password is not masked");
		Reporter.log("TC_LOGIN_02: Password field masks the entered password",true);
	}

	// TC-LOGIN-03
	// Integration
	// Verify Standard User credentials integrate with Inventory page

	@Test(groups = { "Integration", "Functionality", "Smoke", "System" })
	public void TC_LOGIN_03() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String username = data.getExceldata("validLoginData", 1, 0);

		String password = data.getExceldata("validLoginData", 1, 1);

		lp.getLogin(username, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "User was not navigated to Inventory page");
		Reporter.log("TC_LOGIN_03: Standard User credentials integrate with Inventory page",true);
	}

	// TC-LOGIN-04
	// Integration
	// Verify Problem User credentials establish an Inventory session

	@Test(groups = { "Integration", "Functionality", "System" })
	public void TC_LOGIN_04() throws EncryptedDocumentException, IOException {
		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String username = data.getExceldata("validLoginData", 2, 0);

		String password = data.getExceldata("validLoginData", 2, 1);

		lp.getLogin(username, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
				"Problem User was not able to access Inventory");

		Assert.assertTrue(lp.isProductsDisplayed(), "Products page was not displayed");
		Reporter.log("TC_LOGIN_04: Problem User credentials establish an Inventory session",true);
	}

	// TC-LOGIN-05
	// Smoke
	// Verify all essential Login page controls are displayed and usable

	@Test(groups = { "Smoke", "Functionality" })
	public void TC_LOGIN_05() {

		LoginPage lp = new LoginPage(driver);

		Assert.assertTrue(lp.isUsernameDisplayed(), "Username field is not displayed");

		Assert.assertTrue(lp.isPasswordDisplayed(), "Password field is not displayed");

		Assert.assertTrue(lp.isLoginButtonDisplayed(), "Login button is not displayed");

		Assert.assertTrue(lp.isUsernameEnabled(), "Username field is not enabled");

		Assert.assertTrue(lp.isPasswordEnabled(), "Password field is not enabled");

		Assert.assertTrue(lp.isLoginButtonEnabled(), "Login button is not enabled");
		Reporter.log("TC_LOGIN_05: All essential Login page controls are displayed and usable",true);
	}

	// TC-LOGIN-06
	// Smoke
	// Verify Login page returns to empty state after browser refresh

	@Test(groups = { "Smoke", "Functionality" })
	public void TC_LOGIN_06() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String username = data.getExceldata("validLoginData", 3, 0);

		String password = data.getExceldata("validLoginData", 3, 1);

		lp.getUsernametf().sendKeys(username);
		lp.getPasswordtf().sendKeys(password);

		driver.navigate().refresh();

		LoginPage refreshedPage = new LoginPage(driver);

		Assert.assertEquals(refreshedPage.getUsernameValue(), "", "Username field was not cleared after refresh");

		Assert.assertEquals(refreshedPage.getPasswordValue(), "", "Password field was not cleared after refresh");
		Reporter.log("TC_LOGIN_06: Login page returns to empty state after browser refresh",true);
	}

	// TC-LOGIN-07
	// System
	// Verify authenticated session remains active after Inventory refresh

	@Test(groups = { "Functionality", "System", "Integration", "Smoke" })
	public void TC_LOGIN_07() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String username = data.getExceldata("validLoginData", 4, 0);

		String password = data.getExceldata("validLoginData", 4, 1);

		lp.getLogin(username, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login did not reach Inventory");

		driver.navigate().refresh();

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"User session was not maintained after refresh");

		Assert.assertTrue(lp.isProductsDisplayed(), "Inventory page was not available after refresh");
		Reporter.log("TC_LOGIN_07: authenticated session remains active after Inventory refresh",true);
	}

	// TC-LOGIN-08
	// System
	// Verify authenticated user can directly access Inventory page

	@Test(groups = { "System", "Functionality", "Integration" })
	public void TC_LOGIN_08() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		String username = data.getExceldata("validLoginData", 5, 0);

		String password = data.getExceldata("validLoginData", 5, 1);

		lp.getLogin(username, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "User was not authenticated");

		String currentUrl = driver.getCurrentUrl();

		driver.get(currentUrl);

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"Authenticated user could not access Inventory directly");
		Reporter.log("TC_LOGIN_08: Authenticated user can directly access Inventory page",true);
	}

	// TC-LOGIN-09
	// Negative
	// Verify login is rejected when Username is blank

	@Test(groups = { "Functionality", "Negative" })
	public void TC_LOGIN_09() throws EncryptedDocumentException, IOException {

		LoginPage lp = new LoginPage(driver);

		ExcelTestData data = new ExcelTestData();

		// Row containing blank username and password data
		String password = data.getExceldata("invalidLoginData", 4, 1);

		lp.getPasswordtf().sendKeys(password);

		lp.getLoginbutton();

		Assert.assertTrue(lp.printErrorMsg().contains("Username is required"),"Username validation message was not displayed");
		ScreenshotUtility ss = new ScreenshotUtility();
		ss.takeScreenshot(driver, "login_with_invalid_credentials");
		Reporter.log("TC_LOGIN_09: Login is rejected when Username is blank",true);
	}
}