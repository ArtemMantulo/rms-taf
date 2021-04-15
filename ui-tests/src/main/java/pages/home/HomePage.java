package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

        private final SelenideElement welcomeMessage = $x("//h2[@class='MuiTypography-root UserGreetingText MuiTypography-h1']");
        private final SelenideElement productsTab = $x("//a[text()='Products']");
        private final SelenideElement proposalsTab = $x("//a[text()='Proposals']");
        private final SelenideElement importXlsLink = $x("//button[@id='importXLSText']");
        private final SelenideElement importXlsButton = $x("//button[@id='importXLSButton']");

        public void checkWelcomeMessage(String role) {
            switch (role) {
                case "Buyer" :
                    welcomeMessage.shouldHave(Condition.text("Hey, Buyer"));
                    break;
                case "Category Assistant" :
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
