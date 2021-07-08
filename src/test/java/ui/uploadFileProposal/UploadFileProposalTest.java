package ui.uploadFileProposal;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.base.BaseTest;
import ui.common.Listener;
import ui.common.UserActions;
import ui.constants.UserRole;

import static ui.constants.Urls.*;

@Listeners({TestListenerAdapter.class, Listener.class})
@Feature("Upload XLS file")
public class UploadFileProposalTest extends BaseTest {

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
        UserActions.openMainPage()
                .selectUserRole(UserRole.BUYER)
                .login()
                .uploadXls(filePath)
                .checkFileUploadPopup("Success");
    }

    @Test(description = "Check validation for .csv format", groups = "Smoke")
    @Severity(SeverityLevel.CRITICAL)
    public void uploadCsvFile() {
        UserActions.openMainPage()
                .selectUserRole(UserRole.BUYER)
                .login()
                .uploadXls(UPLOAD_CSV_FILE_PATH)
                .checkFileUploadPopup("Failed");
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
