package pages.home;

import com.codeborne.selenide.SelenideElement;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage extends BasePage {
    private final SelenideElement productsResults = $x("//span[@class='results']");
    private final SelenideElement supplierFilter = $x("//a[text()='Products']");
    private final SelenideElement statusFilter = $x("//a[text()='Products']");
    private final SelenideElement tasksFilter = $x("//a[text()='Products']");
    private final SelenideElement productsGrid = $x("//div[@class='MuiContainer-root MuiContainer-maxWidthLg']");
    private final SelenideElement productInTheGrid = $x("//class='jss44'");

    public String verifyProductsResults() {
        return productsResults.getValue();
    }

    public ProductsPage filterProductsBySupplier() {
        return this;
    }

    public ProductsPage filterProductsByStatus() {
        return this;
    }


}
