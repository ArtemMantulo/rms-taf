package uploadXls.positive;


import common.Listener;
import io.qameta.allure.*;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ TestListenerAdapter.class, Listener.class})
@Epic("Upload XLS")
@Feature("Upload XLS file")
public class UploadXlsPositiveTest {
    @Test(priority = 0, description = "Upload XLS file proposal")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Upload XLS file proposal")
    @Story("Login page functionality")
    public void uploadXlsFile() {
        //TODO: implement file upload functionality for both (buyer and CA roles)



    }



}
