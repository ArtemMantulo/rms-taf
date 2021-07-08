package uploadFileProposal;


import com.codeborne.selenide.Selenide;
import common.Listener;
import constants.UserRole;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.home.HomePage;

import static constants.Urls.*;

@Listeners({TestListenerAdapter.class, Listener.class})
@Feature("Upload XLS file")
public class UploadFileProposalTest extends base.BaseTest {

    @DataProvider(name = "FilesToUploadDataProvider")
    public Object[][] FilesToUpload() {
        return new Object[][] {
                { UPLOAD_XLS_FILE_PATH, "XLS format" },
                { UPLOAD_XLSX_FILE_PATH, "XLSX format" }
        };
    }

    @Test(description = "Check success file upload", groups = "Smoke", dataProvider = "FilesToUploadDataProvider")
    @Severity(SeverityLevel.CRITICAL)
    public void uploadProposalFile(String filePath, String fileType) {
        basePage.goToUrl(APP_URL);
        loginPage.selectUserRole(UserRole.BUYER);
        HomePage homePage = loginPage.login();
        homePage.uploadXls(filePath);
        homePage.checkFileUploadPopup("Success");
    }

    @Test(description = "Check validation for .csv format", groups = "Smoke")
    @Severity(SeverityLevel.CRITICAL)
    public void uploadCsvFile() {
        basePage.goToUrl(APP_URL);
        loginPage.selectUserRole(UserRole.BUYER);
        HomePage homePage = loginPage.login();
        homePage.uploadXls(UPLOAD_CSV_FILE_PATH);
        homePage.checkFileUploadPopup("Failed");
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
