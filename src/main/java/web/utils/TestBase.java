package web.utils;

import api.utils.ReportWriter;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by maxim on 1/28/2017.
 */

public class TestBase {
    public WebDriverWrapper driver;
    protected static String URL;
    protected static String environment;
    protected static String browser;
    protected static String branchName;

    @BeforeSuite(groups = {"Config"})
    public void setUp() {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        getProperties();
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.initDriver(browser);
        setURL();
    }

    private void getProperties() {
        browser = System.getProperty("browser");
        environment = System.getProperty("environment");
        branchName = System.getProperty("branchName");

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

    @AfterSuite(groups = {"Config"})
    public void quitDriver() {
        driver.quit();
    }

    public WebDriverWrapper getDriver() {
        return driver;
    }
}
