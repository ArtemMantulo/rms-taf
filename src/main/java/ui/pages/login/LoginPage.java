package ui.pages.login;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.models.UserRole;
import ui.pages.base.BasePage;
import ui.pages.home.HomePage;
import utils.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private final static String LOGIN_BUTTON_ID = "LoginButton";
    private final static String RADIO_BUTTON_BUYER = "//div[@id='SelectedRadioContainer0']//input";
    private final static String RADIO_BUTTON_CATEGORY_ASSISTANT = "//div[@id='SelectedRadioContainer1']//input";

    /**
     * Select role BUYER or CATEGORY_ASSISTANT
     * @param role
     * @return this
     */
    @Step("Select user {role}")
    public LoginPage selectUserRole(UserRole role) {
        switch (role) {
            case BUYER :  {
                $x(RADIO_BUTTON_BUYER).click();
            }
            break;
            case CATEGORY_ASSISTANT :  {
                $x(RADIO_BUTTON_CATEGORY_ASSISTANT).click();
            }
            break;
        }
        return this;
    }

    @Step("Click login button")
    public HomePage login() {
        $id(LOGIN_BUTTON_ID).shouldBe(Condition.visible).click();
        return new HomePage();
    }

}
