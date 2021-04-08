package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static common.Config.BROWSER_AND_PLATFORM;
import static constants.Constant.TimeoutVariables.IMPLICIT_WAIT;
import static org.aspectj.bridge.MessageUtil.fail;

public class CommonActions {

    public static WebDriver createDriver() {
        WebDriver driver = null;
        switch (BROWSER_AND_PLATFORM) {
//            case "CHROME_MAC" :
//                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//                driver = new ChromeDriver();
//                break;
            case "CHROME_WINDOWS" :
                System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//                System.setSecurityManager();
//                System.setProperty("webdriver.chrome.driver", "\\ui-tests\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "FIREFOX_WINDOWS" :
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                fail("incorrect browser name" + BROWSER_AND_PLATFORM);


        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }
}