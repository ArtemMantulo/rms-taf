package ui.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.models.UserRole;
import ui.pages.base.BasePage;
import ui.pages.home.HomePage;
import utils.Logger;

import static com.codeborne.selenide.Selenide.$x;


public class LoginPage extends BasePage {

    private final SelenideElement radioBuyer =
            $x("//div[@id='SelectedRadioContainer0']//input");
    private final SelenideElement radioCategoryAssistant =
            $x("//div[@id='SelectedRadioContainer1']//input");
    private final SelenideElement loginButton = $x("//button[@id='LoginButton']");

    /**
     * Select role BUYER or CATEGORY_ASSISTANT
     * @param role
     * @return this
     */
    @Step("Select user {role}")
    public LoginPage selectUserRole(UserRole role) {
        switch (role) {
            case BUYER :  {
                radioBuyer.click();
            }
            break;
            case CATEGORY_ASSISTANT :  {
                radioCategoryAssistant.click();
            }
            break;
        }
        return this;
    }

    @Step("Click login button")
    public HomePage login() {
        loginButton.shouldBe(Condition.visible).click();
        return new HomePage();
    }

}
