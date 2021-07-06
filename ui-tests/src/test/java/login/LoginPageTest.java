package login;

import common.Listener;
import constants.UserRole;
import io.qameta.allure.*;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.home.HomePage;

import static constants.Urls.APP_URL;

@Listeners({ TestListenerAdapter.class, Listener.class})
@Epic("Login")
@Feature("Test login page")
public class LoginPageTest extends base.BaseTest {
    @Test(priority = 0, description = "Login positive test for Buyer")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify welcome message for buyer role")
    @Story("Login page functionality")
    public void verifyWelcomeMessageForBuyer() {
        basePage.goToUrl(APP_URL);
        loginPage.selectUserRole(UserRole.BUYER.toString());
        HomePage homePage = loginPage.login();
        homePage.checkWelcomeMessage(UserRole.BUYER.toString());
    }
}
