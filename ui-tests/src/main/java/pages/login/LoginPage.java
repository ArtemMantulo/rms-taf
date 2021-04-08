package pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By buttonLogin = By.xpath("//a[@id='loginButton']");
    private final WebElement buttonLoginSubmit = (WebElement) By.xpath("//div[@id='login-submit-button']");

    public LoginPage openLoginDialog() {
        driver.findElement(buttonLogin).click();
        return this;
    }

}
