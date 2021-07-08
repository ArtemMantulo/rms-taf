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
import ui.common.UserActions;
import ui.constants.UserRole;

@Listeners({TestListenerAdapter.class, Listener.class})
@Feature("User login")
public class UserLoginTest extends BaseTest {

    @Test(description = "Login test for Buyer role", groups = "Smoke")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyWelcomeMessageForBuyer() {
        UserActions.openMainPage()
                .selectUserRole(UserRole.BUYER)
                .login()
                .checkWelcomeMessage(UserRole.BUYER);
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
