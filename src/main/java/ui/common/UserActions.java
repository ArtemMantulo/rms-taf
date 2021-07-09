package ui.common;

import io.qameta.allure.Step;
import ui.pages.login.LoginPage;

import static com.codeborne.selenide.AuthenticationType.BASIC;
import static com.codeborne.selenide.Selenide.open;

public class UserActions {

    @Step("Open main website page")
    public static LoginPage openMainPage() {
        open(Config.properties.getPublicSiteUrl(), BASIC, Config.properties.getUser(), Config.properties.getPassword());
        return new LoginPage();
    }

}
