package pages.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final SelenideElement authWidget = $x("//");

    /**
     * Method to navigate specific url
     */
    public void goToUrl(String url) {
        open(url);
    }

    /**
     * Clean the text field first and then enter the text
     */
    protected void clearAndType(SelenideElement element, String value) {
        while(!element.getAttribute("value").equals("")) element.sendKeys(Keys.BACK_SPACE);
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
