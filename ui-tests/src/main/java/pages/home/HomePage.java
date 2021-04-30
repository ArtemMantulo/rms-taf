package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static constants.UserRole.BUYER;

public class HomePage extends BasePage {

        private final SelenideElement welcomeMessage = $x("//h2[@class='MuiTypography-root UserGreetingText MuiTypography-h1']");
        private final SelenideElement productsTab = $x("//a[text()='Products']");
        private final SelenideElement proposalsTab = $x("//a[text()='Proposals']");
        private final SelenideElement importXlsLink = $x("//button[@id='importXLSText']");
        private final SelenideElement importXlsButton = $x("//button[@id='importXLSButton']");



        @Step("Check welcome message for role: {0}")
        public void checkWelcomeMessage() {

            switch (BUYER) {
                case BUYER :
                    welcomeMessage.shouldHave(Condition.text("Hey, Buyer"));
                    break;
                case CATEGORY_ASSISTANT :
                    welcomeMessage.shouldHave(Condition.value("Hey, Category Assistant"));
                    break;
            }
        }

        public ProductsPage goToProductsPage() {
            productsTab.click();
            return new ProductsPage();
        }

        public ProposalsPage goToProposalsPage() {
            productsTab.click();
            return new ProposalsPage();
        }

        public boolean uploadXml(SelenideElement locator) {
            //TODO: implement upload xls method
            return true;
        }

}
