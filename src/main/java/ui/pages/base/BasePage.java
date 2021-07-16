package ui.pages.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final static String AUTH_WIDGET_POPUP = "//";

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
        $x(AUTH_WIDGET_POPUP).shouldBe(Condition.visible);
    }

    /**
     * Check message on the page
     */
    public void checkMessage(String message) {
        $(byText(message)).shouldBe(Condition.visible);
    }

    /**
     * Wrapper for Selenide element by ID
     *
     * @return SelenideElement
     */
    public SelenideElement $id(String id) {
        return $(By.id(id));
    }
}
