package ui.base;

import com.codeborne.selenide.Selenide;
import helpers.WebPropertiesHelper;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import ui.common.Config;
import ui.listeners.TestListener;


import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

@Log4j
@Listeners({TestListener.class})
public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        WebPropertiesHelper.init();
    }

    /**
     * A static initialization block in order to clean the folders with reports and screenshots before build starts
     */
    static {
        LOGGER.info("START TIME - " + LocalTime.now());
        LOGGER.info("Clear reports directory builds/reports/ ...");
        File allureResults = new File("target/allure-results");
        if(allureResults.isDirectory()) {
            for (File item : Objects.requireNonNull(allureResults.listFiles())) {
                item.delete();
            }
        }
        if(Config.CLEAR_REPORTS_DIR) {
            File reports = new File("build/reports/tests/");
            if (reports.isDirectory()) {
                for (File item : Objects.requireNonNull(reports.listFiles())) {
                    item.delete();
                }
            }
        }
        File downloads = new File("builds/downloads/");
        if (downloads.isDirectory()) {
            for(File item : Objects.requireNonNull(downloads.listFiles())) {
                item.delete();
            }
        }
    }

}
