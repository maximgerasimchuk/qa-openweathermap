package web.utils;

import api.utils.ReportWriter;
import api.utils.YamlFileReader;
import web.ConfigEntity;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.util.logging.Level;

/**
 * Created by maxim on 1/28/2017.
 */
public class WebDriverFactory {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String IE = "ie";
    public static final String SAFARI = "safari";

    private static WebDriver driver;
    private static WebDriverWrapper driverWrapper;
    private static ConfigEntity param;

    public static WebDriverWrapper initDriver(String browser) {
        Dimension dimension = new Dimension(1920, 1080);

        if (browser.equals(FIREFOX)) {
            DesiredCapabilities capabilities = setDefaultFirefoxCapabilities();
            driver = new FirefoxDriver(capabilities);
            driver.manage().window().maximize();
            driver.manage().window().setSize(dimension);
            driverWrapper = new WebDriverWrapper(driver);
        } else if (browser.equals(CHROME)) {
            param = new ConfigEntity();
            param = YamlFileReader.fromYaml(ConfigEntity.class, System.getProperty("user.dir") + "/src/test/config/webDriverConfig.yml");

            File chromeDriverPath = new File(param.getChromeDriverPath());
            if (chromeDriverPath.exists()) {
                ReportWriter.logInfo("Set property webdriver.chrome.driver = '" + chromeDriverPath.getAbsolutePath().toString() + "'");
                System.setProperty("webdriver.chrome.driver", chromeDriverPath.getAbsolutePath());
            }

            DesiredCapabilities capabilities = setDefaultChromeCapabilities();
            driver = new ChromeDriver(capabilities);
            driver.manage().window().maximize();
            driverWrapper = new WebDriverWrapper(driver);
        } else if (browser.equals(IE)) {
            param = new ConfigEntity();
            param = YamlFileReader.fromYaml(ConfigEntity.class, System.getProperty("user.dir") + "/src/test/config/webDriverConfig.yml");

            File ieDriverPath = new File(param.getIeDriverPath());
            if (ieDriverPath.exists()) {
                System.setProperty("webdriver.ie.driver", ieDriverPath.getAbsolutePath());
            }
            DesiredCapabilities capabilities = setDefaultIeCapabilities();
            driver = new InternetExplorerDriver(capabilities);
            driver.manage().window().setSize(dimension);
            driverWrapper = new WebDriverWrapper(driver);
        }
//        else if (browser.equals(SAFARI)) {
//            driver = new SafariDriver();
//            driverWrapper = new WebDriverWrapper(driver);
//        }
        else {
            Assert.fail("No information about launching '" + browser + "' browser!!!");
        }

        return driverWrapper;
    }

    public static WebDriverWrapper getDriverWrapper() {
        return driverWrapper;
    }

    private static DesiredCapabilities setDefaultChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
//        options.addArguments("user-data-dir=D:\\UserDataDir");
//        options.addArguments("-incognito");
        options.addArguments("--no-sandbox");

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps.setCapability("ignoreZoomSetting", true);

        return caps;
    }

    private static DesiredCapabilities setDefaultFirefoxCapabilities() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setEnableNativeEvents(true);
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

        return firefoxCapabilities;
    }

    private static DesiredCapabilities setDefaultIeCapabilities() {
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("ignoreZoomSetting", true);

        return caps;
    }
}
