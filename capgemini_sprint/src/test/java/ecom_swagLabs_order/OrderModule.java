package ecom_swagLabs_order;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass_utility.Baseclass;
import generic_utility.ScreenshotUtility;
import swag_labs_pom_pages.CartPage;
import swag_labs_pom_pages.CheckoutCompletePage;
import swag_labs_pom_pages.CheckoutOverViewPage;
import swag_labs_pom_pages.CheckoutStep1Page;
import swag_labs_pom_pages.HomePage;
import swaglabs_exceldata.ExcelTestData;

public class OrderModule extends Baseclass {

	// TC-ORDER-01
	// Functionality
	// Verify that a product can be added to the cart

	@Test(groups = {"Functionality","Smoke", "Integration"})
	public void TC_ORDER_01() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 3, 0);

		hp.addProductToCartDirectly(productName);

		Assert.assertEquals(hp.getNoofcartitems(), "1", "Product was not added to cart");
		Reporter.log("TC_ORDER_01: Product successfully added to cart) ",true);
	}

	

	// TC-ORDER-03
	// Integration
	// Verify that the selected product is displayed in Cart

	@Test(groups = {"Functionality","Integration"})
	public void TC_ORDER_03() throws Exception {

	    HomePage hp = new HomePage(driver);
	    CartPage cp = new CartPage(driver);

	    ExcelTestData data = new ExcelTestData();
	    String productName = data.getExceldata("orderProducts", 2, 0);

	    hp.addProductToCartDirectly(productName);

	    Assert.assertEquals(hp.getProductButtonText(productName),"Remove","Product was not added successfully");

	    hp.clickCarticon();

	    Assert.assertTrue(cp.isProductDisplayed(productName),"Selected product is not displayed in Cart");

	    Reporter.log("TC_ORDER_03: Selected  Product displayed in Cart", true);
	}

	// TC-ORDER-04
	// Integration
	// Verify that checkout information is transferred to Overview page

	@Test(groups = {"Integration","Functionality","System"})
	public void TC_ORDER_04() throws EncryptedDocumentException, IOException, InterruptedException {

		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);
		CheckoutStep1Page checkout = new CheckoutStep1Page(driver);
		CheckoutOverViewPage overview = new CheckoutOverViewPage(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 1, 0);

		String firstName = data.getExceldata("orderProducts", 1, 1);

		String lastName = data.getExceldata("orderProducts", 1, 2);

		String postalCode = data.getExceldata("orderProducts", 1, 3);

		hp.addProductToCartDirectly(productName);

		hp.clickCarticon();
		Thread.sleep(2000);

		cp.clickCheckoutButton();
		Thread.sleep(2000);

		checkout.checkoutinformation(firstName, lastName, postalCode);
		Thread.sleep(2000);

		checkout.clickContinueButton();
		Thread.sleep(2000);

		Assert.assertTrue(overview.isOverviewPageDisplayed(), "Checkout Overview page was not displayed");
		Reporter.log("TC_ORDER_04: checkout information is transferred to Overview page ",true);
	}

	// TC-ORDER-05
	// Smoke
	// Verify that Continue Shopping returns to Inventory page

	@Test(groups = {"Smoke","Functionality", "Integration"})
	public void TC_ORDER_05() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 1, 0);

		hp.addProductToCartDirectly(productName);

		hp.clickCarticon();

		cp.clickContinueShopping();

		
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"Continue Shopping did not return to Inventory");
	    Reporter.log("TC_ORDER_05: Continue Shopping returns to Inventory page",true);
	}

	// TC-ORDER-06
	// Smoke
	// Verify that valid checkout details open the Overview page

	@Test(groups =  {"Smoke","Functionality", "Integration"})
	public void TC_ORDER_06() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);
		CheckoutStep1Page checkout = new CheckoutStep1Page(driver);
		CheckoutOverViewPage overview = new CheckoutOverViewPage(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 2, 0);

		String firstName = data.getExceldata("orderProducts", 2, 1);

		String lastName = data.getExceldata("orderProducts", 2, 2);

		String postalCode = data.getExceldata("orderProducts", 2, 3);

		hp.addProductToCartDirectly(productName);

		hp.clickCarticon();

		cp.clickCheckoutButton();

		checkout.checkoutinformation(firstName, lastName, postalCode);

		checkout.clickContinueButton();

		Assert.assertTrue(overview.isOverviewPageDisplayed(), "Overview page was not displayed");
		Reporter.log("TC_ORDER_06: valid checkout details open the Overview page",true);
	}

	// TC-ORDER-07
	// System
	// Verify complete order flow from Login to Order Confirmation

	@Test(groups =  {"Smoke","Functionality", "Integration","System"})
	public void TC_ORDER_07() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);

		CheckoutStep1Page checkout = new CheckoutStep1Page(driver);

		CheckoutOverViewPage overview = new CheckoutOverViewPage(driver);

		CheckoutCompletePage complete = new CheckoutCompletePage(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 6, 0);

		String firstName = data.getExceldata("orderProducts", 5, 1);

		String lastName = data.getExceldata("orderProducts", 3, 2);

		String postalCode = data.getExceldata("orderProducts", 1, 3);

		hp.addProductToCartDirectly(productName);

		hp.clickCarticon();

		cp.clickCheckoutButton();

		checkout.checkoutinformation(firstName, lastName, postalCode);

		checkout.clickContinueButton();

		overview.clickFinishButton();

		Assert.assertTrue(complete.isOrderConfirmationDisplayed(), "Order confirmation was not displayed");
		Reporter.log("TC_ORDER_07: Successfully complete order flow from Login to Order Confirmation",true);
	}

	// TC-ORDER-08
	// System
	// Verify that Cancel from Checkout returns to Cart

	@Test(groups = {"System", "Functionality", "Integration"})
	public void TC_ORDER_08() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);
		CheckoutStep1Page checkout = new CheckoutStep1Page(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 4, 0);

		hp.addProductToCartDirectly(productName);

		hp.clickCarticon();

		cp.clickCheckoutButton();

		checkout.clickCancleButton();

		Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cancel did not return to Cart");
		Reporter.log("TC_ORDER_08: Cancel from Checkout returns to Cart",true);
	}

	// TC-ORDER-09
	// Negative
	// Verify checkout is prevented when mandatory fields are blank

	@Test(groups = {"Negative","Functionality", "Smoke"})
	public void TC_ORDER_09() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);
		CheckoutStep1Page checkout = new CheckoutStep1Page(driver);

		ExcelTestData data = new ExcelTestData();

		String productName = data.getExceldata("orderProducts", 2, 0);

		hp.addProductToCartDirectly(productName);

		hp.clickCarticon();

		cp.clickCheckoutButton();

		// No data is entered

		checkout.clickContinueButton();

		Assert.assertTrue(checkout.isErrorMessageDisplayed(), "Validation error was not displayed");
		Reporter.log("TC_ORDER_09: checkout is prevented when mandatory fields are blank",true);
	}

	
	
//	@Test(groups = {"Negative","System"})
//	public void TC_ORDER_10() throws IOException {
//		HomePage hp = new HomePage(driver);
//		CartPage cp = new CartPage(driver);
//		CheckoutStep1Page checkout = new CheckoutStep1Page(driver);
//		ScreenshotUtility ss = new ScreenshotUtility();
//
//		hp.clickCarticon();
//		cp.clickCheckoutButton();
//
//		if (driver.getCurrentUrl().contains("checkout-step-one")) {
//			ExcelTestData data = new ExcelTestData();
//
//			String firstName = data.getExceldata("orderProducts", 3, 1);
//			String lastName = data.getExceldata("orderProducts", 5, 2);
//			String postalCode = data.getExceldata("orderProducts", 6, 3);
//
//			checkout.checkoutinformation(firstName, lastName, postalCode);
//			checkout.clickContinueButton();
//		}
//
//		if (driver.getCurrentUrl().contains("checkout-step-two")) {
//			CheckoutOverViewPage overview = new CheckoutOverViewPage(driver);
//			overview.clickFinishButton();
//		}
//
//		CheckoutCompletePage complete = new CheckoutCompletePage(driver);
//
//		if (complete.isOrderConfirmationDisplayed()) {
//			ss.takeScreenshot(driver, "Checkout_without_product");
//			Assert.fail("Defect: Order was completed without adding a product");
//		}
//
//		Assert.assertFalse(complete.isOrderConfirmationDisplayed(), "Order confirmation appeared for an empty cart");
//		Reporter.log("TC_ORDER_10:Failed the testcase because order is placed without product",true);
//	}
//	

}
