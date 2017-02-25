package api.utils;

import org.testng.Reporter;

/**
 * Created by maxim on 1/21/2017.
 */
public class ReportWriter {
    public static final org.apache.log4j.Logger FILE_LOGGER = org.apache.log4j.Logger.getLogger(ReportWriter.class);
    public static final org.testng.log4testng.Logger REPORT_LOGGER =  org.testng.log4testng.Logger.getLogger(ReportWriter.class);

    private static final String PAGE_NOT_LOADED = "\"%s\" page didn't load!";

    public static void logPageNotLoaded(String s) {
        FILE_LOGGER.error(s);
        Reporter.log(String.format(PAGE_NOT_LOADED, "<br>" + s));
    }

    public static void logError(String s) {
        FILE_LOGGER.error(s);
        Reporter.log("<br><font color=\"red\">[ERROR]: </font>" + s);
    }

    public static void logFault(String s) {
        FILE_LOGGER.error("SOAP Fault: " + s);
        Reporter.log("<br><font color=\"red\">[SOAP Fault]: </font>" + s);
    }

    public static void logDuration(Long s) {
        FILE_LOGGER.info("DURATION: " + s);
        Reporter.log("<br><font color=\"DarkBlue\">[DURATION]: </font>" + s + " ms");
    }

    public static void logException(String s) {
        FILE_LOGGER.info("EXCEPTION: " + s);
        Reporter.log("<br><font color=\"red\">[EXCEPTION]: </font>" + s);
    }

    public static void logWarnings(String s) {
        FILE_LOGGER.info(s);
        Reporter.log("<br><font color=\"brown\">[WARNING]: </font>" + s);
    }

    public static void logConsoleWarnings(String s) {
        FILE_LOGGER.info("[CONSOLE]:" + s);
        Reporter.log("<br><font color=\"brown\">[CONSOLE]: </font>" + s);
    }

    public static void logXmlDifference(String s) {
        FILE_LOGGER.info("[XML DIFF]:" + s);
        Reporter.log("<br><font color=\"red\">[XML DIFF]: </font>" + s);
    }

    public static void logBrowserError(String s) {
        FILE_LOGGER.error(s);
        Reporter.log("<br><font color=\"red\">[ERROR]: " + s + "</font>");
    }

    public static void logScreenShot(String s) {
        FILE_LOGGER.info("Taking ScreenShot: " + s);
        Reporter.log("<br>" + "Saved <a href=" + s + ">Screenshot</a>");
    }

    public static void logXmlFile(String type, String s) {
        switch (type) {
            case "Request":
                FILE_LOGGER.info("logXmlRequest into file: " + s);
                Reporter.log("<br>" + "Saved Request <a href=" + s + " target=\"_blank\">xml</a>");
                break;
            case "Response":
                FILE_LOGGER.info("logXmlResponse into file: " + s);
                Reporter.log("<br>" + "Saved Response <a href=" + s + " target=\"_blank\">xml</a>");
                break;
            default:
                break;
        }
    }

    public static void logHtmlFile(String type, String s) {
        switch (type) {
            case "Request":
                FILE_LOGGER.info("logHtmlRequest into file: " + s);
                Reporter.log("<br>" + "Saved Request <a href=" + s + " target=\"_blank\">html</a>");
                break;
            case "Response":
                FILE_LOGGER.info("logHtmlResponse into file: " + s);
                Reporter.log("<br>" + "Saved Response <a href=" + s + " target=\"_blank\">html</a>");
                break;
            default:
                break;
        }
    }

    public static void logJsonFile(String type, String s) {
        switch (type) {
            case "Request":
                FILE_LOGGER.info("logJsonRequest into file: " + s);
                Reporter.log("<br>" + "Saved Request <a href=" + s + ">Json</a>");
                break;
            case "Response":
                FILE_LOGGER.info("logJsonResponse into file: " + s);
                Reporter.log("<br>" + "Saved Response <a href=" + s + ">Json</a>");
                break;
            default:
                break;
        }
    }

    public static void logInfo(String s) {
        FILE_LOGGER.info(s);
        Reporter.log("<br>[INFO]: " + s);
    }

    public static void logUrl(String s) {
        FILE_LOGGER.info("[INFO]: Current URL = " + s);
        Reporter.log("<br>" + "Current URL: <a href=" + s + " target=\"_blank\">" + s + "</a>");
    }

    public static void logDebug(String s) {
        FILE_LOGGER.debug(s);
        Reporter.log("<br>[DEBUG]" + s);
    }

    public static void logWarn(String s) {
        FILE_LOGGER.warn(s);
        Reporter.log("<br>[WARNING]" + s);
    }

    public static void logFatal(String s) {
        FILE_LOGGER.fatal(s);
        Reporter.log("<br>[FATAL]" + s);
    }

    public static void logTestStep(String s) {
        FILE_LOGGER.info("TestStep: " + s);
//        Reporter.log("<br><font size=\"3\" color=\"#347235\">TestStep: " + s + "</font>");
        Reporter.log("<br><font size=\"3\" color=\"\tForestGreen\">TestStep: " + s + "</font>");
    }

    public static void logTestCaseName(String s) {
        FILE_LOGGER.info("-------------------------------------------------------------------------------------");
        FILE_LOGGER.info("TEST CASE NAME: " + s);
    }

    public static void logTestDescription(String s) {
        FILE_LOGGER.info("TEST DESCRIPTION: " + s);
        FILE_LOGGER.info("-------------------------------------------------------------------------------------");
    }

    public static void logSeparator() {
        FILE_LOGGER.info("______________________");
        Reporter.log("<br>______________________");
    }
}
