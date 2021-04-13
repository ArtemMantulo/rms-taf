package login.positive;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.home.HomePage;

import static constants.Constant.Urls.LOGIN_PAGE_URL;

public class LoginPageTest extends BaseTest {
    @Test
    public void openLoginPage() {
        basePage.goToUrl(LOGIN_PAGE_URL);
        loginPage.selectRoleBuyer();
        HomePage homePage = loginPage.login();
        homePage.checkWelcomeMessage("Buyer");
    }
}
