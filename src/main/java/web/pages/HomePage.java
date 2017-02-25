package web.pages;

import api.utils.DateTimeHelper;
import api.utils.ReportWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 1/28/2017.
 */
public class HomePage extends BasePage {

    private static final By SEARCH_INPUT = By.xpath("//*[@placeholder='Your city name']");
    private static final By SEARCH_BUTTON = By.xpath("//button[@class='btn btn-orange' and @type='submit']");
    private static final By ALL_LINKS = By.xpath("//*[@href]");
    private String date;
    private String city;

    public HomePage(String url) {
        super(url);
    }

    public static By getSearchInput() {
        return SEARCH_INPUT;
    }

    public static By getSearchButton() {
        return SEARCH_BUTTON;
    }

    public static By getAllLinks() {
        return ALL_LINKS;
    }

    public static String isLinkBroken(URL url) throws Exception {
        String response = "";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.connect();
            response = connection.getResponseMessage();
            connection.disconnect();
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void open() {
        ReportWriter.logTestStep("Open openweathermap.org.");
        driver.get(pageUrl);
        driver.waitUrlLoaded();
        ReportWriter.logUrl(driver.getCurrentUrl());
    }

    public void fillSearch(String city) {
        ReportWriter.logInfo("Fill search form : " + city);
        inputField.click(SEARCH_INPUT);
        inputField.clear(SEARCH_INPUT);
        inputField.sendKeys(SEARCH_INPUT, city);
        setSearchParameters(city);
    }

    private void setSearchParameters(String cityData) {
        city = cityData;
        date = DateTimeHelper.getDateTime(0);
    }

    public void search() {
        ReportWriter.logTestStep("Search.");
        baseElement.click(SEARCH_BUTTON);
        baseElement.waitUntilInvisibilityOfElement(ResultPage.getResultCity());
    }

    public List getListOfAllLinks() {
        List<WebElement> list = new ArrayList();
        list = driver.findElements(By.tagName("a"));
        list.addAll(driver.findElements(By.tagName("img")));

        List finalList = new ArrayList();
        for (WebElement element : list) {
            if (element.getAttribute("href") != null) {
                finalList.add(element);
            }
        }

        return finalList;
    }


}
