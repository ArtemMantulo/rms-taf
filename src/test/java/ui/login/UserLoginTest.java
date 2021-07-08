package ui.login;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.base.BaseTest;
import ui.common.Listener;
import ui.constants.UserRole;
import ui.pages.home.HomePage;

import static ui.constants.Urls.APP_URL;

@Listeners({TestListenerAdapter.class, Listener.class})
@Feature("User login")
public class UserLoginTest extends BaseTest {

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
