package common;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import static common.Config.CLEAR_COOKIES;
import static common.Config.HOLD_BROWSER_OPEN;

public class CommonActions {

    @AfterTest
    public static void clearBrowserCookiesAndStorage() {
        if (CLEAR_COOKIES) {
            try {
                Selenide.clearBrowserCookies();
                Selenide.clearBrowserLocalStorage();
                Selenide.executeJavaScript("window.sessionStorage.clear()");
            } catch (Exception e) {
                // TODO: add logger
            }
        }
    }

    @AfterSuite
    public static void closeBrowser() {
        if(!HOLD_BROWSER_OPEN) {
            Selenide.closeWebDriver();
        }
    }

}