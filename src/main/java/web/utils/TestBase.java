package web.utils;

import api.utils.ReportWriter;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.*;

/**
 * Created by maxim on 1/28/2017.
 */

@Listeners(CustomTestListener.class)
public class TestBase {
    private static ThreadLocal<WebDriverWrapper> drivers = new ThreadLocal<>();
    private String selenoidURL;
    protected static String URL;
    protected static String environment;
    protected static String browser;
    protected static String branchName;

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void setUp() {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        getProperties();
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        drivers.set(webDriverFactory.initDriver(browser, selenoidURL));
        setURL();
    }

    private void getProperties() {
        browser = System.getProperty("browser");
        environment = System.getProperty("environment");
        branchName = System.getProperty("branchName");
        selenoidURL = "http://127.0.0.1:4444/wd/hub";

        if (browser == null || browser.equals("")) {
            browser = "chrome";
        }

        if (environment == null || environment.equals("")) {
            environment = "production";
        }

        if (branchName == null || branchName.equals("")) {
            branchName = "master";
        }

        ReportWriter.logInfo("---Parameters---");
        ReportWriter.logInfo("Browser: '" + browser + "'");
        ReportWriter.logInfo("Environment: '" + environment + "'");
        ReportWriter.logInfo("BranchName: '" + branchName + "'");
        ReportWriter.logInfo("---Parameters---");
    }

    private void setURL() {
        if (environment.equalsIgnoreCase("production")){
            URL = "http://openweathermap.org/";
        }

    }

    @AfterSuite()
    public void quitDriver() {
        drivers.get().quit();
    }

    public WebDriverWrapper getDriver() {
        return drivers.get();
    }
}
