package core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Logger;

import java.net.URI;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static enums.EnvProperties.CONTAINER_ENGINE;
import static enums.WebEnvProperties.BROWSER;
import static enums.WebEnvProperties.BROWSER_VERSION;


public class WebDriverManager {
    private static WebDriver driver;
    public static boolean HOLD_BROWSER_OPEN = false;
    public static boolean CLEAR_COOKIES = true;
    public static final boolean CLEAR_REPORTS_DIR = true;


    public static void setupDriver() {

        if (Boolean.parseBoolean(CONTAINER_ENGINE.getValue())) {
            Configuration.browser = BROWSER.getValue();
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName(BROWSER.getValue());
            browser.setVersion(BROWSER_VERSION.getValue());
            browser.setCapability("enableVNC", true);
            try {
                driver = new RemoteWebDriver(URI.create("http://0.0.0.0:4444/wd/hub").toURL(), browser);
            } catch (Exception e) {
                Logger.log(e.getMessage());
                throw new Error("Failed to init webDriver");
            }
            setWebDriver(driver);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            Configuration.browser = BROWSER.getValue();
            Configuration.browserSize = "1920x1080";
            Configuration.holdBrowserOpen = HOLD_BROWSER_OPEN;
            Configuration.reportsFolder = "builds/reports/tests";
            Configuration.timeout = 5000;
        }
    }
}
