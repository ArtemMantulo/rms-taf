package login;

import com.codeborne.selenide.Selenide;
import common.Listener;
import constants.UserRole;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
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
        homePage.checkWelcomeMessage(UserRole.BUYER);
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
