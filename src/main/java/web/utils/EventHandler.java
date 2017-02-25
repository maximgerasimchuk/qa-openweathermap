package web.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created by maxim on 1/28/2017.
 */
public class EventHandler implements WebDriverEventListener {
    WebDriver driver;

    public EventHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
//        System.out.println("inside method afterChangeValueOf on " + arg0.toString());
    }

    public void afterClickOn(WebElement arg0, WebDriver arg1) {
//        boolean b = true;
//        Level level = Level.SEVERE;
//        for (LogEntry logEntry : driver.manage().logs().get(LogType.BROWSER).filter(level)) {
////            ReportWriter.logBrowserError("BROWSER_LOG AfterClickOn on | "+ arg0.toString() + " |     " +  new Date(logEntry.getTimestamp()) + " " + logEntry.getLevel() + " " + logEntry.getMessage());
////            ReportWriter.logError("[" + logEntry.getLevel() + "] " + logEntry.getMessage());
//            ReportWriter.logConsoleWarnings(logEntry.getMessage());
//                b = false;
//        }
////        Assert.assertTrue(b);
    }

    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
//        System.out.println("Find happened on " + arg1.toString() + " Using method " + arg0.toString());
    }

    public void afterNavigateBack(WebDriver arg0) {
//        System.out.println("Inside the after navigateback to " + arg0.getCurrentUrl());
    }

    public void afterNavigateForward(WebDriver arg0) {
//        System.out.println("Inside the afterNavigateForward to " + arg0.getCurrentUrl());
    }

    public void afterNavigateTo(String arg0, WebDriver arg1) {
//        System.out.println("Inside the afterNavigateTo to " + arg0);
    }

    public void afterScript(String arg0, WebDriver arg1) {
//        System.out.println("Inside the afterScript to, Script is " + arg0);
    }

    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
//        System.out.println("Inside the beforeChangeValueOf method");
    }

    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
//        System.out.println("About to click on the " + arg0.toString());
    }

    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
//        System.out.println("Just before finding element: " + arg0.toString() + " on browser: " + arg2.toString() );
    }

    public void beforeNavigateBack(WebDriver arg0) {
//        System.out.println("Just before beforeNavigateBack " + arg0.getCurrentUrl());
    }

    public void beforeNavigateForward(WebDriver arg0) {
//        System.out.println("Just before beforeNavigateForward " + arg0.getCurrentUrl());
    }

    public void beforeNavigateTo(String arg0, WebDriver arg1) {
//        System.out.println("Just before beforeNavigateTo " + arg0);
    }

    public void beforeScript(String arg0, WebDriver arg1) {
//        System.out.println("Just before beforeScript " + arg0);
    }

    public void onException(Throwable arg0, WebDriver arg1) {
//        System.out.println("Exception occured at " + arg0.getMessage());
    }

    public void beforeNavigateRefresh(WebDriver var1){}

    public void afterNavigateRefresh(WebDriver var1){}
}
