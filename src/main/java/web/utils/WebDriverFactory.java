package web.utils;

import api.utils.ReportWriter;
import api.utils.YamlFileReader;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
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

    public static WebDriverWrapper initDriver(String browser, String selenoidURL) {
        Dimension dimension = new Dimension(1280, 800);
        DesiredCapabilities capabilities = setDefaultChromeCapabilities();

        if (selenoidURL == null || selenoidURL == "") {

            if (browser.equals(FIREFOX)) {
                capabilities = setDefaultFirefoxCapabilities();
                driver = new FirefoxDriver(capabilities);
                driver.manage().window().maximize();
                driver.manage().window().setSize(dimension);
            } else if (browser.equals(CHROME)) {
                param = new ConfigEntity();
                param = YamlFileReader.fromYaml(ConfigEntity.class, System.getProperty("user.dir") + "/src/test/config/webDriverConfig.yml");

                File chromeDriverPath = new File(param.getChromeDriverPath());
                if (chromeDriverPath.exists()) {
                    ReportWriter.logInfo("Set property webdriver.chrome.driver = '" + chromeDriverPath.getAbsolutePath().toString() + "'");
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath.getAbsolutePath());
                }

                capabilities = setDefaultChromeCapabilities();
                driver = new ChromeDriver(capabilities);
                driver.manage().window().maximize();
            } else if (browser.equals(IE)) {
                param = new ConfigEntity();
                param = YamlFileReader.fromYaml(ConfigEntity.class, System.getProperty("user.dir") + "/src/test/config/webDriverConfig.yml");

                File ieDriverPath = new File(param.getIeDriverPath());
                if (ieDriverPath.exists()) {
                    System.setProperty("webdriver.ie.driver", ieDriverPath.getAbsolutePath());
                }
                capabilities = setDefaultIeCapabilities();
                driver = new InternetExplorerDriver(capabilities);
                driver.manage().window().setSize(dimension);
            } else if (browser.equals(SAFARI)) {
                driver = new SafariDriver();
            }
        } else {
            try {
                ReportWriter.logInfo("Start initialize RemoteWebDriver");
                driver = new RemoteWebDriver(URI.create(selenoidURL).toURL(), capabilities);
                driver.manage().window().setSize(dimension);
                ReportWriter.logInfo("Stop initialize RemoteWebDriver");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return new WebDriverWrapper(driver);
    }

    public static WebDriverWrapper getDriverWrapper() {
        return driverWrapper;
    }

    private static DesiredCapabilities setDefaultChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps.setBrowserName("chrome");
        caps.setVersion("66.0");
        caps.setCapability("ignoreZoomSetting", true);
        caps.setCapability("enableVNC", true);
        caps.setCapability("enableVideo", false);

        return caps;
    }

    private static DesiredCapabilities setDefaultFirefoxCapabilities() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setEnableNativeEvents(true);
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        firefoxCapabilities.setCapability("enableVNC", false);
        firefoxCapabilities.setCapability("enableVideo", false);

        return firefoxCapabilities;
    }

    private static DesiredCapabilities setDefaultIeCapabilities() {
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability("ignoreZoomSetting", true);
        caps.setCapability("enableVNC", true);
        caps.setCapability("enableVideo", false);

        return caps;
    }
}
