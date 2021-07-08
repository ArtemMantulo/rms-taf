package ui.common;

import com.codeborne.selenide.Selenide;
import framework.ConfigProvider;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import ui.pages.login.LoginPage;

import java.util.logging.Logger;

import static com.codeborne.selenide.AuthenticationType.BASIC;
import static ui.common.Config.CLEAR_COOKIES;
import static ui.common.Config.HOLD_BROWSER_OPEN;
import static com.codeborne.selenide.Selenide.*;

public class CommonActions {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Listener.class));


    @AfterTest
    public static void clearBrowserCookiesAndStorage() {
        if (CLEAR_COOKIES) {
            try {
                Selenide.clearBrowserCookies();
                Selenide.clearBrowserLocalStorage();
                Selenide.executeJavaScript("window.sessionStorage.clear()");
            } catch (Exception e) {
                LOGGER.info("SESSION_STORAGE/COOKIES has not been cleared!!!!!");
            }
        }
    }

    @AfterSuite
    public static void closeBrowser() {
        if (!HOLD_BROWSER_OPEN) {
            Selenide.closeWebDriver();
        }
    }

}