package pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private final SelenideElement buttonLogin = $x("//a[@id='loginButton']");
    private final SelenideElement buttonLoginSubmit =  $x("//div[@id='login-submit-button']");

    public LoginPage openLoginDialog() {
        buttonLogin.shouldBe(Condition.visible).click();
        return this;
    }

}
