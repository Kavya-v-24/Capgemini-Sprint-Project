
package swag_labs_pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HamburgerMenupage {

    WebDriver driver;

    public HamburgerMenupage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutbutton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allitembutton;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutbutton;

    @FindBy(xpath = "//a[@data-test='reset-sidebar-link']")
    private WebElement resetbutton;

    @FindBy(id = "dynamic_catalog_sidebar_link")
    private WebElement dynamiccatalog;

    @FindBy(id = "dynamic_catalog_lazy_load_link")
    private WebElement lazyloadbutton;

    @FindBy(id = "dynamic_catalog_spinner_link")
    private WebElement spinnerbutton;

    @FindBy(id = "dynamic_catalog_slider_link")
    private WebElement sliderbutton;
    
    @FindBy(id="react-burger-cross-btn")
    private WebElement closeMenubutton;

    // All Items

    public void clickAllitembutton() {
        allitembutton.click();
    }

    // About

    public void clickAboutbutton() {
        aboutbutton.click();
    }

    public boolean checkAbout() {
        return driver.getCurrentUrl().startsWith("https://saucelabs.com/");
    }

    // Reset App State

    public void clickResetbutton() {
        resetbutton.click();
    }

    // Dynamic Catalog

    public void clickDynamicCatalog() {
        dynamiccatalog.click();
    }

    // Lazy Load

    public void clickLazyLoad() {
        lazyloadbutton.click();
    }

    public boolean checkLazyLoadPage() {
        return driver.getCurrentUrl().contains("dynamic-catalog-lazy-load");
    }

    // Spinner

    public void clickSpinner() {
        spinnerbutton.click();
    }

    public boolean checkSpinnerProduct() {
        return driver.findElement( By.xpath("//div[@data-test='spinner-item-0-name' and text()='Sauce Labs Bike Light']")).isDisplayed();
    }

    // Slider

    public void clickSlider() {
        sliderbutton.click();
    }

    public boolean checkSliderProduct() {
        return driver.findElement( By.xpath("//div[@data-test='dynamic-catalog-slider-item-name']")).isDisplayed();
    }

    

    public void clickLogout() {
        logoutbutton.click();
    }
    
    public void clickCloseMenubutton() {
        closeMenubutton.click();
    }
    
    public void checkCartBadge()
    {
    	Assert.assertEquals( driver.findElements(By.xpath("//span[@class='shopping_cart_badge']")).size(), 0, "Cart badge was not cleared after Reset App State");
    
    }
}

