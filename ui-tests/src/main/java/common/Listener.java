package common;

//
//public class Listener{
//
//    private static final Logger LOGGER = Logger.getLogger(Listener.class);
//
//
//    @AfterTest
//    public void afterEach() {
//        clearBrowserCookiesAndStorage();
//    }
//
//    public void testFailed(ITestListener listener, Throwable cause) {
//        LOGGER.info("Test {} - FAILED", listener.onTestFailure(););
//    }
//
//    @Attachment(value = "Attachment screenshot", type = "image/png")
//    public byte[] attachScreenshotToAllure() {
//        if(WebDriverRunner.hasWebDriverStarted())
//            return ((TakeScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//
//
//
//
//}
