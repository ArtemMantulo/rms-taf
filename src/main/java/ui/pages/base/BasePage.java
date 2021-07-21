package ui.pages.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import utils.Logger;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    public Logger logger =  Logger.get("Logger");
    private final SelenideElement authWidget = $x("//");

    /**
     * Method to navigate specific url
     */
    @Step("Open page {url}")
    public void goToUrl(String url) {
        open(url);
    }

    /**
     * Clean the text field first and then enter the text
     */
    protected void clearAndType(SelenideElement element, String value) {
        while (!Objects.equals(element.getAttribute("value"), "")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.setValue(value);
    }

    /**
     * Wait for auth widget is visible
     */
    public void checkIsDisplayedAuthWidget() {
        authWidget.shouldBe(Condition.visible);
    }

    /**
     * Check message on the page
     */
    public void checkMessage(String message) {
        $(byText(message)).shouldBe(Condition.visible);
    }
}
