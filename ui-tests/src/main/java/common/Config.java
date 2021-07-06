package common;

import com.codeborne.selenide.Configuration;

public class Config {
    /**
     * Specify the browser for test
     *
     * chrome / firefox / opera / edge / ie
     */
    public static  String BROWSER_NAME = "chrome";

    /**
     * Clean browser cookies after each iteration
     */
    public static boolean CLEAR_COOKIES = true;

    /**
     * Keep browser open after all scenarios/tests
     */
    public static boolean HOLD_BROWSER_OPEN = false;

    /**
     * Clear reports directory before each test run
     */
    public static final boolean CLEAR_REPORTS_DIR = true;

    static {
        Configuration.holdBrowserOpen = HOLD_BROWSER_OPEN;
        Configuration.reportsFolder = "builds/reports/tests";
        Configuration.browser = BROWSER_NAME;
        Configuration.timeout = 5000;
    }

}
