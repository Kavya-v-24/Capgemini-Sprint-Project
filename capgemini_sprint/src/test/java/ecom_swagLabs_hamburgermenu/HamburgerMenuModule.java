package ecom_swagLabs_hamburgermenu;



import java.io.IOException;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass_utility.Baseclass;
import swag_labs_pom_pages.HamburgerMenupage;
import swag_labs_pom_pages.HomePage;
import swag_labs_pom_pages.LoginPage;

public class HamburgerMenuModule extends Baseclass {

    // TC-HAMBURGER-01
    // Functionality
    // Verify All Items navigates to Inventory

    @Test(groups = {"Functionality", "Smoke", "Integration"})
    public void TC_HAMBURGER_01() {

        HomePage hp = new HomePage(driver);
        HamburgerMenupage menu = new HamburgerMenupage(driver);

        hp.clickMenubutton();
        menu.clickAllitembutton();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"All Items did not navigate to Inventory");
        menu.clickCloseMenubutton();

        Reporter.log("TC-HAMBURGER-01: All Items navigated to Inventory successfully", true);
              
    }

    
    

    // TC-HAMBURGER-02
    // Functionality
    // Verify Lazy Load navigation

    @Test(groups = {"Functionality", "Integration"})
    public void TC_HAMBURGER_02() {

        HomePage hp = new HomePage(driver);
        HamburgerMenupage menu = new HamburgerMenupage(driver);

        hp.clickMenubutton();
        menu.clickDynamicCatalog();
        menu.clickLazyLoad();

        Assert.assertTrue( menu.checkLazyLoadPage(), "Lazy Load page was not opened");

        Reporter.log( "TC-HAMBURGER-02: Lazy Load page opened successfully", true);
    }


    
    
    // TC-HAMBURGER-03
    // Functionality
    // Verify Spinner displays a product

    @Test(groups = {"Functionality", "Integration"})
    public void TC_HAMBURGER_03() {

        HomePage hp = new HomePage(driver);
        HamburgerMenupage menu = new HamburgerMenupage(driver);

        hp.clickMenubutton();
        menu.clickDynamicCatalog();
        menu.clickSpinner();

        Assert.assertTrue(driver.getCurrentUrl().contains("dynamic-catalog-spinner"), "Spinner page was not opened");

        Assert.assertTrue(menu.checkSpinnerProduct(),"Sauce Labs Bike Light was not displayed on Spinner page");

        Reporter.log("TC-HAMBURGER-03: Spinner page opened and product displayed",true);
    }

    
    

    // TC-HAMBURGER-04
    // Functionality
    // Verify Slider displays a product

    @Test(groups = {"Functionality", "Integration"})
    public void TC_HAMBURGER_04() {

        HomePage hp = new HomePage(driver);
        HamburgerMenupage menu = new HamburgerMenupage(driver);

        hp.clickMenubutton();
        menu.clickDynamicCatalog();
        menu.clickSlider();

        Assert.assertTrue(driver.getCurrentUrl().contains("dynamic-catalog-slider"),"Slider page was not opened");

        Assert.assertTrue( menu.checkSliderProduct(), "Slider product was not displayed");

        Reporter.log("TC-HAMBURGER-04: Slider page opened and product displayed",true);
    }


    
    
    // TC-HAMBURGER-05
    // Integration, System
    // Verify About navigation

    @Test(groups = {"Integration", "System"})
    public void TC_HAMBURGER_05() throws IOException {

        HomePage hp = new HomePage(driver);
        HamburgerMenupage menu = new HamburgerMenupage(driver);

        hp.clickMenubutton();
        menu.clickAboutbutton();

        Assert.assertTrue(menu.checkAbout(),"About did not navigate to the Sauce Labs website");

        Reporter.log("TC-HAMBURGER-05: About navigated to Sauce Labs successfully",true);

        // Return to SauceDemo and log in again for Baseclass logout

        driver.get(getCommonData("url"));

        LoginPage lp = new LoginPage(driver);
        lp.getLogin( getCommonData("username"), getCommonData("password"));

        Assert.assertTrue( driver.getCurrentUrl().contains("inventory"), "Could not return to the logged-in Inventory page");

        Reporter.log("TC-HAMBURGER-05: Returned to Inventory for teardown",true);
    }

    
    

    // TC-HAMBURGER-06
    // Functionality, Integration
    // Verify Reset App State clears the cart

    @Test(groups = {"Functionality", "Integration", "System"})
    public void TC_HAMBURGER_06() {

        HomePage hp = new HomePage(driver);
        HamburgerMenupage menu = new HamburgerMenupage(driver);

        hp.addProductToCartDirectly("Sauce Labs Backpack");

        Assert.assertEquals(hp.getNoofcartitems(),"1","Product was not added to the cart");

        hp.clickMenubutton();
        menu.clickResetbutton();
        menu.checkCartBadge();
        Reporter.log("TC-HAMBURGER-06: Reset App State cleared the cart", true);
        menu.clickCloseMenubutton();
    }
}



