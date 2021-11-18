package ui.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import ui.models.UserRole;
import ui.pages.base.BasePage;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.format;

public class LoginPage extends BasePage {

    class Resultat {

        String result;
        String coinNumber;

        public Resultat(String result,String coinNumber) {
            this.result = result;
            this.coinNumber = coinNumber;
        }
    }

    private Float coinsNumber;
    private List<Double> coinNumberCulculated;
    private final static String TERM_AND_CONDITIONS = "//i";
    private final static String LOGIN = "//span[text()='LOGIN']";
    private final static String WAX_WALLET_BUTTON = "//span[text()='WAX Cloud Wallet']";
    private final static String LOGIN_INPUT = "//input[@name='userName']";
    private final static String PASSWORD_INPUT = "//input[@name='password']";
    private final static String LOGIN_BUTTON = "//button[text()='Login']";
    private final static String BEGIN_ADVENTURE = "//span[text()='Begin adventure']";
    private final static String SEND_TO_ADVENTURE = "//div[@class = 'one-slot-number']//span[text()='%s']//parent::div//following-sibling::div//span[text()='SEND to adventure']";
    private final static String CLOSE_BUTTON = "//span[text()='close']";
    private final static String STAKING_BUTTON = "//div[@class = 'one-slot-number']//span[text()='%s']//parent::div//following-sibling::div//*[text()='STAKING']";
    private final static String HERO_IS_RESTING = "//div[@class = 'one-slot-number']//span[text()='%s']//parent::div//following-sibling::div//*[text()='hero is resting']";
    private final static String REWARD_NUMBER = "//div[@class = 'header-list-one bam-header']//span";


    //div[@class = "one-slot-number"]//span[text()="%s"]//parent::div//following-sibling::div//span[text()='SEND to adventure']
    //div[@class = "one-slot-number"]//span[text()="%s"]//parent::div//following-sibling::div//*[text()='hero is resting']

    private final static String RADIO_BUTTON_BUYER = "//span[text()='LOGIN']";
    private final static String RADIO_BUTTON_CATEGORY_ASSISTANT = "//div[@id='SelectedRadioContainer1']//input";

    /**
     * Select role BUYER or CATEGORY_ASSISTANT
     *
     * @param role
     * @return this
     */
    @Step("Select user {role}")
    public LoginPage selectUserRole(UserRole role) {
        switch (role) {
            case BUYER: {
                $x(RADIO_BUTTON_BUYER).click();
            }
            break;
            case CATEGORY_ASSISTANT: {
                $x(RADIO_BUTTON_CATEGORY_ASSISTANT).click();
            }
            break;
        }
        return this;
    }

    @Step("Click login button")
    public LoginPage clickOnTermsAndConditions() {
        $x(TERM_AND_CONDITIONS).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Click login button")
    public LoginPage clickOnLogin() {
        sleep(3000);
        $x(LOGIN).shouldBe(Condition.visible).click();
        sleep(3000);
        $x(WAX_WALLET_BUTTON).shouldBe(Condition.visible).click();
        sleep(3000);
        String oldWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        sleep(3000);
        Set<String> newWindows = WebDriverRunner.getWebDriver().getWindowHandles();
        sleep(10000);
        newWindows.size();

        List<String> a = newWindows.stream().collect(Collectors.toList());
        String newWindow = a.get(1);
        WebDriverRunner.getWebDriver().switchTo().window(newWindow);
        sleep(2000);
        $x(LOGIN_INPUT).shouldBe(Condition.visible).sendKeys("Artemmantulo@gmail.com");
        $x(PASSWORD_INPUT).shouldBe(Condition.visible).sendKeys("Newpassword199321!");
        sleep(10000);
        $x(LOGIN_BUTTON).shouldBe(Condition.visible).click();
        WebDriverRunner.getWebDriver().switchTo().window(oldWindow);
        sleep(2000);
        $x(BEGIN_ADVENTURE).shouldBe(Condition.visible).click();
        sleep(2000);
        return this;
    }

    @Step("Click login button")
    public LoginPage beginAdvanture(int slotNumber) {
        for (int i = 1; i <= 10000; i++) {
            String coinNumbers  = $x(REWARD_NUMBER).getText().replace(" BAM", "");
            coinsNumber = Float.parseFloat(coinNumbers);
             Resultat result = sendToAdventureButtonCheckFinal(slotNumber);
            if (result.result.equals("Need to wait")) {
                sleep(60000);
            } else if (result.result.equals("Clicked!")) {
                sleep(3000);
                //System.out.println(result.coinNumber);
                Float coinNumbersAfterClick  = Float.parseFloat($x(REWARD_NUMBER).getText().replace(" BAM", ""));
                Float coinsEarned = coinNumbersAfterClick - coinsNumber;
                System.out.println("Coins after click: " + coinNumbersAfterClick +"Coins number before click: " + coinNumbers + "Earned coins:" + coinsEarned);
                coinsNumber = coinNumbersAfterClick;
                break;
            }
        }
        return this;
    }

//    public void calculatedCoins(String coinNumber) {
//       double initialReward = Double.parseDouble(coinNumber);
//       coinNumberCulculated.add(initialReward);
//       System.out.println(coinNumberCulculated.stream().mapToDouble(i -> i).sum());
//    }


 /*   @Step("Click login button")
    public LoginPage beginAdvantureLast(int slotNumber) {
        for (int i = 1; i <= 10000; i++) {
            String result = sendToAdventureButtonCheckLast(slotNumber);
            if (result.equals("Need to wait")) {
                sleep(200000);
            } else if (result.equals("Clicked")) {
                sleep(3000);
                break;
            }
        }
        return this;
    }*/

    @Step("Click login button")
    public LoginPage waitTwoHours() {
        sleep(300000); return this;
    }

/*    public String sendToAdventureButtonCheck(int slotNumber) {
            String result = null;
            sleep(3000);
            Boolean isHeroResting = $x(format(HERO_IS_RESTING, slotNumber)).exists();
            Boolean isNextHeroResting = $x(format(HERO_IS_RESTING, slotNumber + 1)).exists();
            if (isHeroResting == true && isNextHeroResting == false) {
                System.out.println("Checked: Need to wait " + date.toString());
                result = "Go to another panda";
            } else if (isHeroResting == true) {
                System.out.println("Checked: Need to wait " + date.toString());
                result = "Need to wait";
            } else if ($x(format(SEND_TO_ADVENTURE, slotNumber)).isDisplayed()) {
                sleep(3000);
                $x(format(SEND_TO_ADVENTURE, slotNumber)).shouldBe(Condition.enabled).click();
                System.out.println("Checked: Clicked " + date.toString());
                result = "Clicked!";
                sleep(3000);
                closeRewardPopUP();
            }
        return result;
    }*/
/*    public String sendToAdventureButtonCheckLast(int slotNumber) {
            String result = null;
            sleep(3000);
            Boolean isHeroResting = $x(format(HERO_IS_RESTING, slotNumber)).exists();
            if (isHeroResting == true) {
                System.out.println("Checked: Need to wait " + date.toString());
                result = "Need to wait";
            } else if ($x(format(SEND_TO_ADVENTURE, slotNumber)).isDisplayed()) {
                sleep(3000);
                $x(format(SEND_TO_ADVENTURE, slotNumber)).shouldBe(Condition.enabled).click();
                System.out.println("Checked: Clicked " + date.toString());
                result = "Clicked!";
                sleep(3000);
                closeRewardPopUP();
            } return result;
    }*/

    public String closeRewardPopUP() {
        sleep(15000);
        String result = null;
        if ($x(CLOSE_BUTTON).isDisplayed()) {
            $x(CLOSE_BUTTON).shouldBe(Condition.visible).click();
            Boolean isExists = $x(CLOSE_BUTTON).exists();
            sleep(6000);
            System.out.println("Close button window is visible: " + isExists);
            result = "Close reward button is clicked";
        } else if (!$x(CLOSE_BUTTON).isDisplayed()) {
            sleep(6000);
            result = "Close reward pop-up is not shown";
        }
        return result;
    }

    public Resultat sendToAdventureButtonCheckFinal(int slotNumber) {
        String rewardNumber = null;
        String result = null;
        sleep(3000);
        Boolean isHeroResting = $x(format(HERO_IS_RESTING, slotNumber)).exists();
        if (isHeroResting == true) {
            System.out.println("Checked: Need to wait slot" + slotNumber + new Date());
            result = "Need to wait";
        } else if ($x(format(SEND_TO_ADVENTURE, slotNumber)).isDisplayed()) {
            sleep(3000);
            $x(format(SEND_TO_ADVENTURE, slotNumber)).shouldBe(Condition.visible).click();
            sleep(6000);
            if ($x(CLOSE_BUTTON).exists()) {
                sleep(6000);
                //rewardNumber = $x(REWARD_NUMBER).shouldBe(Condition.visible).getText();
            } else {
            }
            System.out.println("Checked: Clicked slot " + slotNumber + rewardNumber + new Date());
            closeRewardPopUP();
            if ($x(format(SEND_TO_ADVENTURE, slotNumber)).exists())
            {
                result = "Need to wait";
            } else {
                result = "Clicked!";
            }
        }
        return new Resultat(result, rewardNumber);
    }

    @Step("Click login button")
    public LoginPage beginAdventureLoop() {
        for (int i = 1; i <= 10000; i++) {
          beginAdvanture(1);
           beginAdvanture(2);
           beginAdvanture(3);
           beginAdvanture(4);
           waitTwoHours();
        }
        return this;
    }
}