package login.positive;

import base.BaseTest;
import common.Listener;
import constants.Roles;
import io.qameta.allure.*;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.home.HomePage;

import static constants.Urls.LOGIN_PAGE_URL;

@Listeners({ TestListenerAdapter.class, Listener.class})
@Epic("Login")
@Feature("Test login page")
public class LoginPageTest extends BaseTest {
    @Test(priority = 0, description = "Login positive test for Buyer")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login test for buyer role")
    @Story("Login page functionality")
    public void openLoginPage() {
        basePage.goToUrl(LOGIN_PAGE_URL);
        loginPage.selectRoleBuyer();
        HomePage homePage = loginPage.login();
        homePage.checkWelcomeMessage(Roles.BUYER);
    }
}
