package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

        private final SelenideElement header = $x("");
        private final SelenideElement welcomeMessage = $x("//h2[@class='MuiTypography-root MuiTypography-h1']");
        private final SelenideElement toolbar = $x("");
        private final SelenideElement importXlsLink = $x("");
        private final SelenideElement importXlsButton = $x("");

        public HomePage checkWelcomeMessage(String role) {
            switch (role) {
                case "Buyer" :
                    welcomeMessage.shouldHave(Condition.text("Hey, Buyer"));
                    break;
                case "Category Assistant" :
                    welcomeMessage.shouldHave(Condition.value("Hey, Category Assistant"));
                    break;
            }
            return this;
        }

}
