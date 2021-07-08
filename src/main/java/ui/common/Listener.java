package ui.common;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import java.util.logging.Logger;

public class Listener implements ITestListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Listener.class));

    @AfterTest
    public void afterEach() {
        CommonActions.clearBrowserCookiesAndStorage();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test {} - FAILED: " + result);
    }

}
