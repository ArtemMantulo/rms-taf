package base;

import pages.base.BasePage;
import pages.login.LoginPage;

public class BaseTest {
//    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage();
    protected LoginPage loginPage = new LoginPage();


    static {
        //TODO: add clear report folder before test start
    }

//    @AfterTest
//    void clearCookiesAndLocalStorage() {
//        if (CLEAR_COOKIES) {
//            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//            driver.manage().deleteAllCookies();
//            javascriptExecutor.executeScript("window.sessionStorage.clear()");
//        }
//    }
//    @AfterSuite
//    void close() {
//        if (!HOLD_BROWSER_OPEN) {
//            driver.close();
//        }
//    }

}
