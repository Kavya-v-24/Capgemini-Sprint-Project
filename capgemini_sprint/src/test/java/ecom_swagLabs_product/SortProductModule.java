package ecom_swagLabs_product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass_utility.Baseclass;
import swag_labs_pom_pages.HomePage;
import swaglabs_exceldata.ExcelTestData;

public class SortProductModule extends Baseclass {

	@Test(groups = {"Functionality", "Smoke"})
	public void TC_SORT_01() {
		// checking when sort dropdown is clicked whether options displayed or not

		HomePage hp = new HomePage(driver);
		hp.clickProductsortdd();
		Assert.assertTrue(hp.isOptionDisplayed());
		Reporter.log("TC_SORT_01(Product Sort dropdown displays the available sorting option.)",true);


	}

	// TC-SORT-02
	@Test(groups = { "Functionality", "Smoke"})
	public void TC_SORT_02() throws EncryptedDocumentException, IOException {

		// Verify all available Product Sort options can be selected

		HomePage hp = new HomePage(driver);

		ExcelTestData opt = new ExcelTestData();

		String option1 = opt.getExceldata("sortProducts", 1, 0);
		String option2 = opt.getExceldata("sortProducts", 2, 0);
		String option3 = opt.getExceldata("sortProducts", 3, 0);
		String option4 = opt.getExceldata("sortProducts", 4, 0);

		hp.clickProductsortdd();
		hp.sortProducts(option1);

		hp.clickProductsortdd();
		hp.sortProducts(option2);

		hp.clickProductsortdd();
		hp.sortProducts(option3);

		hp.clickProductsortdd();
		hp.sortProducts(option4);

		Assert.assertTrue(hp.isProductSortDisplayed(), "Product Sort dropdown is not displayed");
		Reporter.log("TC_SORT_02(all available Product Sort options can be selected successfully.)",true);
	}

	// TC-SORT-03
	@Test(groups = {"System", "Integration", "Functionality", "Smoke"})
	public void TC_SORT_03() throws EncryptedDocumentException, IOException {

		// Verify Name (A to Z) updates the product list

		HomePage hp = new HomePage(driver);

		ExcelTestData opt = new ExcelTestData();

		String option = opt.getExceldata("sortProducts", 1, 0);

		hp.clickProductsortdd();
		hp.sortProducts(option);

		List<String> actualProducts = hp.getProductNames();

		List<String> expectedProducts = new ArrayList<>(actualProducts);

		Collections.sort(expectedProducts);

		Assert.assertEquals(actualProducts, expectedProducts, "Products are not sorted in Name A to Z order");
		Reporter.log("TC_SORT_03(selecting Name A to Z arranges products in ascending alphabetical order)",true);
	}

	// TC-SORT-04
	@Test(groups = { "Integration", "Functionality"})
	public void TC_SORT_04() throws EncryptedDocumentException, IOException {

		// Verify Price (low to high) updates the product list

		HomePage hp = new HomePage(driver);

		ExcelTestData opt = new ExcelTestData();

		String option = opt.getExceldata("sortProducts", 3, 0);

		hp.clickProductsortdd();
		hp.sortProducts(option);

		List<Double> actualPrices = hp.getProductPrices();

		List<Double> expectedPrices = new ArrayList<>(actualPrices);

		Collections.sort(expectedPrices);

		Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted from low price to high price");
		Reporter.log("TC_SORT_04( selecting Price Low to High arranges products in ascending price order)",true);
	}

	// TC-SORT-05
	@Test(groups = {"Functionality", "Smoke"})
	public void TC_SORT_05() {

		// Verify Product Sort dropdown is available on Inventory page

		HomePage hp = new HomePage(driver);

		Assert.assertTrue(hp.isProductSortDisplayed(), "Product Sort dropdown is not displayed");
		Reporter.log("TC_SORT_05(Product Sort dropdown is available on the Inventory page)",true);
	}

	// TC-SORT-06
	@Test(groups = {"Functionality", "Smoke"})
	public void TC_SORT_06() throws EncryptedDocumentException, IOException {

		// Verify basic Product Sort operation works after Login

		HomePage hp = new HomePage(driver);

		ExcelTestData opt = new ExcelTestData();

		String option = opt.getExceldata("sortProducts", 1, 0);

		hp.clickProductsortdd();
		hp.sortProducts(option);

		Assert.assertTrue(hp.isProductSortDisplayed(), "Product Sort operation failed");
		Reporter.log("TC_SORT_06( Product Sort operation performed successfully on the Inventory page)",true);
	}

	// TC-SORT-07
	@Test(groups = {"System", "Integration", "Functionality"})
	public void TC_SORT_07() throws EncryptedDocumentException, IOException {

		// Verify complete flow of Login, Product Sort and product verification

		HomePage hp = new HomePage(driver);

		ExcelTestData opt = new ExcelTestData();

		String option = opt.getExceldata("sortProducts", 1, 0);

		hp.clickProductsortdd();
		hp.sortProducts(option);

		List<String> actualProducts = hp.getProductNames();

		List<String> expectedProducts = new ArrayList<>(actualProducts);

		Collections.sort(expectedProducts);

		Assert.assertEquals(actualProducts, expectedProducts, "Complete Product Sort flow failed");
		Reporter.log("TC_SORT_07(complete flow of Login, Product Sort and product-order verification)",true);
	}

	// TC-SORT-08
	@Test(groups = {"System", "Integration", "Functionality"})
	public void TC_SORT_08() throws EncryptedDocumentException, IOException {

		// Verify product list updates when different sorting options are selected

		HomePage hp = new HomePage(driver);

		ExcelTestData opt = new ExcelTestData();

		String option1 = opt.getExceldata("sortProducts", 1, 0);
		String option2 = opt.getExceldata("sortProducts", 2, 0);
		String option3 = opt.getExceldata("sortProducts", 3, 0);
		String option4 = opt.getExceldata("sortProducts", 4, 0);

		hp.clickProductsortdd();
		hp.sortProducts(option1);

		List<String> aToZ = hp.getProductNames();

		hp.clickProductsortdd();
		hp.sortProducts(option2);

		List<String> zToA = hp.getProductNames();

		hp.clickProductsortdd();
		hp.sortProducts(option3);

		List<Double> lowToHigh = hp.getProductPrices();

		hp.clickProductsortdd();
		hp.sortProducts(option4);

		List<Double> highToLow = hp.getProductPrices();

		List<String> expectedAToZ = new ArrayList<>(aToZ);
		Collections.sort(expectedAToZ);

		List<String> expectedZToA = new ArrayList<>(expectedAToZ);
		Collections.reverse(expectedZToA);

		List<Double> expectedLowToHigh = new ArrayList<>(lowToHigh);
		Collections.sort(expectedLowToHigh);

		List<Double> expectedHighToLow = new ArrayList<>(expectedLowToHigh);
		Collections.reverse(expectedHighToLow);

		Assert.assertEquals(aToZ, expectedAToZ, "A to Z sorting failed");

		Assert.assertEquals(zToA, expectedZToA, "Z to A sorting failed");

		Assert.assertEquals(lowToHigh, expectedLowToHigh, "Low to High sorting failed");

		Assert.assertEquals(highToLow, expectedHighToLow, "High to Low sorting failed");
		Reporter.log("TC_SORT_08( all available Product Sort options produce their respective sorting orders)",true);
	}

	@Test (groups = { "Functionality", "Smoke"})

	public void TC_SORT_09() throws EncryptedDocumentException, IOException {
		// checking whether selected option is working
		HomePage hp = new HomePage(driver);
		hp.clickProductsortdd();

		ExcelTestData opt = new ExcelTestData();
		String option = opt.getExceldata("sortProducts", 1, 0);
		hp.sortProducts(option);
		Reporter.log("TC_SORT_09(Product Sort functionality remains available after performing a sorting operation)",true);
	}

}
