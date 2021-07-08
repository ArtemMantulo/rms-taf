package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.UserRole;
import io.qameta.allure.Step;
import pages.base.BasePage;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

        private final SelenideElement welcomeMessage =
                $x("//h2[@class='MuiTypography-root UserGreetingText MuiTypography-h1']");
        private final SelenideElement productsTab = $x("//a[text()='Products']");
        private final SelenideElement proposalsTab = $x("//a[text()='Proposals']");
        private final SelenideElement importXlsLink = $x("//button[@id='importXLSText']");
        private final SelenideElement importXlsButton = $x("//button[@id='importXLSButton']");
        private final SelenideElement importXlsPopup = $x("//input[@class='dzu-input']");
        private final SelenideElement successUploadedPopup =
                $x("//div[@class='NotificationContent']/div[@class='TitleAndButton']");

        @Step("Check welcome message for role: {userRole}")
        public void checkWelcomeMessage(UserRole userRole) {
            switch (userRole) {
                case BUYER :
                    welcomeMessage.shouldHave(Condition.text("Hey, Buyer"));
                    break;
                case CATEGORY_ASSISTANT :
                    welcomeMessage.shouldHave(Condition.value("Hey, Category Assistant"));
                    break;
            }
        }

        @Step("Open Products Tab")
        public ProductsPage goToProductsPage() {
            productsTab.click();
            return new ProductsPage();
        }

        @Step("Open Proposals Tab")
        public ProposalsPage goToProposalsPage() {
            productsTab.click();
            return new ProposalsPage();
        }

        @Step("Upload file proposal")
        public HomePage uploadXls(String filePath) {
            importXlsButton.click();
            File file = new File(filePath);
            $(importXlsPopup).uploadFile(file);
            return this;
        }

    @Step("Verify popup when file proposal is uploaded")
    public void checkFileUploadPopup(String expectedCondition) {
        switch (expectedCondition) {
            case "Success":
                $(successUploadedPopup).shouldHave(Condition.text("Success"));
                break;
            case "Failed":
                $(successUploadedPopup).shouldBe(Condition.visible).
                        shouldHave(Condition.text("Oops! Something went wrong"));
                break;
        }
    }

}
