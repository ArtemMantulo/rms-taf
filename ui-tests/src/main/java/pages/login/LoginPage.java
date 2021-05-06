package pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.base.BasePage;
import pages.home.HomePage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private final SelenideElement radioBuyer =
            $x("//div[@id='SelectedRadioContainer0']//input[@id='LoginRadio']");
    private final SelenideElement radioCategoryAssistant =
            $x("//div[@id='SelectedRadioContainer1']//input[@id='LoginRadio']");
    private final SelenideElement loginButton = $x("//button[@id='LoginButton']");


    public LoginPage selectRoleBuyer() {
        radioBuyer.click();
        return this;
    }

    public LoginPage selectRoleCategoryAssistant() {
        radioCategoryAssistant.click();
        return this;
    }

    public HomePage login() {
        loginButton.shouldBe(Condition.visible).click();
        return new HomePage();
    }

}
