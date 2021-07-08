package ui.common;

import framework.ConfigProvider;
import io.qameta.allure.Step;
import ui.pages.login.LoginPage;

import static com.codeborne.selenide.AuthenticationType.BASIC;
import static com.codeborne.selenide.Selenide.open;

public class UserActions {

    @Step("Open main website page")
    public static LoginPage openMainPage() {
        ConfigProvider config = new ConfigProvider();
        open(config.getPublicSiteUrl(), BASIC, config.getUser(), config.getPassword());
        return new LoginPage();
    }

}
