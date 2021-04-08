package common;

public class Config {
    /**
     * Specify the browser and platform for test
     *
     * CHROME_MAC - TODO: do we need to test at MAC ??
     * CHROME_WINDOWS
     * FIREFOX_WINDOWS
     */
    public static final String BROWSER_AND_PLATFORM = "FIREFOX_WINDOWS";

    /**
     * Clean browser cookies after each iteration
     */
    public static final boolean CLEAR_COOKIES = true;

    /**
     * Keep browser open after all scenarios/tests
     */
    public static final boolean HOLD_BROWSER_OPEN = false;

    /**
     * Clear reports directory before each test run
     */
    public static final boolean CLEAR_REPORTS_DIR = true;
}
