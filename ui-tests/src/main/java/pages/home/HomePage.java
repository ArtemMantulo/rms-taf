package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.base.BasePage;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static constants.UserRole.BUYER;

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

        @Step("Check welcome message for role: {0}")
        public void checkWelcomeMessage(String userRole) {

            switch (userRole) {
                case "Buyer" :
                    welcomeMessage.shouldHave(Condition.text("Hey, Buyer"));
                    break;
                case "Category Assistant" :
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

        @Step("Upload XLS file")
        public void uploadXls(String filePath) {
            importXlsButton.click();
//            importXlsPopup.shouldBe(Condition.visible);

            //TODO: wrap into try-catch
            File file = new File(filePath);
            $(importXlsPopup).uploadFile(file);

        }

    /**
     * Verify popup when .xls file uploaded
     *
     * @param expectedCondition
     */
    public void checkFileUploadPopup(String expectedCondition)
        {
            switch (expectedCondition) {
                case "Success" :
                    $(successUploadedPopup).shouldHave(Condition.text("Success"));
                    break;
                case "Failed" :
                    $(successUploadedPopup).shouldBe(Condition.visible).shouldHave(Condition.text("Oops! Something went wrong"));
                    break;
            }
        }

}
