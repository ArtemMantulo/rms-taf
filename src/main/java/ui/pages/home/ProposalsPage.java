package ui.pages.home;

import com.codeborne.selenide.SelenideElement;
import ui.pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class ProposalsPage extends BasePage {

    private final SelenideElement proposalsResults = $x("//span[@class='results']");
    private final SelenideElement supplierFilter = $x("//a[text()='Products']");
    private final SelenideElement statusFilter = $x("//a[text()='Products']");
    private final SelenideElement tasksFilter = $x("//a[text()='Products']");
    private final SelenideElement productsGrid = $x("//a[text()='Products']");
    private final SelenideElement productInTheGrid = $x("//a[text()='Products']");
}
