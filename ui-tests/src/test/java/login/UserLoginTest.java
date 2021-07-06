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
@Feature("User login")
public class UserLoginTest extends base.BaseTest {

    @Test(description = "Login test for Buyer role", groups = "Smoke")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyWelcomeMessageForBuyer() {
        basePage.goToUrl(APP_URL);
        loginPage.selectUserRole(UserRole.BUYER);
        HomePage homePage = loginPage.login();
        homePage.checkWelcomeMessage(UserRole.CATEGORY_ASSISTANT);
    }
}
