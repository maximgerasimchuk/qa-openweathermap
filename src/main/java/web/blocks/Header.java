package web.blocks;

import web.pages.HomePage;
import org.openqa.selenium.By;

/**
 * Created by maxim on 1/28/2017.
 */
public class Header extends BaseBlock {
    private static final By OPEN_WEATHER_MAP_LOGO = By.xpath("//a[@class='navbar-brand']/img");
    private static final By WEATHER = By.xpath("//a[text()='Weather']");
    private static final By MAPS_DROPDOWN_TOGGLE = By.xpath("//a[text()='Maps ']");
    private static final By MAPS_DROPDOWN_MENU = By.xpath("//a[text()='Maps ']/following-sibling::ul[@class='dropdown-menu']");
    private static final By API = By.xpath("//a[text()='API']");
    private static final By PRICE = By.xpath("//a[text()='Price']");
    private static final By PARTNERS = By.xpath("//a[text()='Partners']");
    private static final By STATIONS = By.xpath("//a[text()='Stations']");
    private static final By WIDGETS = By.xpath("//a[text()='Widgets']");
    private static final By NEWS = By.xpath("//a[text()='News']");
    private static final By ABOUT_DROPDOWN_TOGGLE = By.xpath("//a[text()='About ']");
    private static final By ABOUT_DROPDOWN_MENU = By.xpath("//a[text()='About ']/following-sibling::ul[@class='dropdown-menu']");

    public void goToHomePage(){
        baseElement.click(OPEN_WEATHER_MAP_LOGO);
        baseElement.waitUntilElementIsVisible(HomePage.getSearchInput());
        baseElement.waitUntilInvisibilityOfElement(HomePage.getSearchInput(), 60);
        baseElement.waitAndVerifyElementPresent(HomePage.getSearchInput());
    }
}
