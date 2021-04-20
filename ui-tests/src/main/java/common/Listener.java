package common;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import java.util.logging.Logger;

import static common.CommonActions.clearBrowserCookiesAndStorage;

public class Listener implements ITestListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Listener.class));

    @AfterTest
    public void afterEach() {
        clearBrowserCookiesAndStorage();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test {} - FAILED: " + result);
    }

}
