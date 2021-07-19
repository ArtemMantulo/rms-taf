package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static enums.EnvProperties.*;
import static java.util.Objects.nonNull;

public class Utils {
    private static Logger logger = Logger.get(Utils.class);
    public static final String DEFAULT_ENV = "qa";

    private static final String KILL = "taskkill /F /IM \"%s\" /T";
    private static final String KILL_REMOTE_PROCESS = "taskkill /s %s -u %s -p %s /F /IM \"%s\" /T";
    private static final String TASKLIST = "tasklist";
    private static final String REMOTE_PROCESS = System.getProperty("user.dir") + "\\..\\operators\\src\\main\\java\\tools\\Pslist.exe \\\\%s -u %s -p %s -e \"%s\"";
    private static final int MAX_CNC_ALIVE_TIME = 7200000;
    private static final String CNC_ID_AUTOMATION_NAME = "AUTO-";
    private static final int DEFAULT_RETRY_ACTION_INTERVAL_MILLIS = 1000;
    private static final int DEFAULT_NUMBER_OF_RETRIES = 3;
    static List<Class> m_classes = new ArrayList<>();


    public static <T> T parseJson(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, clazz);
    }

    public static <T> T parseJson(String json, TypeToken<T> clazz) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, clazz.getType());
    }

    public static String parseJsonFile(String filePath, String key) {
        JSONParser parser = new JSONParser();
        String value = "";
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            value = (String) jsonObject.get(key);

        } catch (Exception e) {
            e.getMessage();
        }
        return value;
    }

    public static String getTestRailSuiteName(Class<?> clazz) {
        Epic annotation = clazz.getAnnotation(Epic.class);
        if (nonNull(annotation)) {
            return annotation.value();
        } else {
            return null;
        }
    }


    public static void waitForThreadsToFinish(Thread... threads) {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean isSessionAlive(WebDriver driver) {
        try {
            driver.getPageSource();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void deleteDirectory(String path) {
        logger.info("Trying to delete " + path);
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            logger.info("Failed to delete " + path + " with error: " + e.getMessage());
        }
    }


    public static String getPropertyEnv() {
        String env = nonNull(TEST_ENV.getValue()) ? TEST_ENV.getValue() : TEST_ENV.getName();
        if (env.contains("qa"))
            return "qa";
        else if (env.contains("dev"))
            return "dev";
        else
            return env.equals("") ? DEFAULT_ENV : env;
    }

    public static String getEnv() {
        String env = nonNull(TEST_ENV.getValue()) ? TEST_ENV.getValue() : TEST_ENV.getName();
        if (env.contains("qa"))
            return "qa";
        else if (env.contains("dev"))
            return "dev";
        else if (env.contains("stage"))
            return "stage";
        else if (env.contains("local"))
            return "local";
        else
            return env.equals("") ? DEFAULT_ENV : env;
    }

    public static String getServicesEnv() {
        return nonNull(SERVICES_ENV.getValue()) ? SERVICES_ENV.getValue() : SERVICES_ENV.getName();
    }

    public static String getWebEnv() {
        return nonNull(WEB_ENV.getValue()) ? WEB_ENV.getValue() : WEB_ENV.getName();
    }

    public static String getJiraItem(ITestResult iTestResult) {
        Issue jiraItem = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Issue.class);

        if (Objects.isNull(jiraItem)) {
            return null;
        } else {
            return jiraItem.value();
        }
    }

    public static int[] getTestRailIds(ITestResult iTestResult) {
        TmsLink tmsLinkAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TmsLink.class);
        TmsLinks tmsLinksAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TmsLinks.class);
        if (Objects.nonNull(tmsLinkAnnotation)) {
            return new int[]{Integer.parseInt(tmsLinkAnnotation.value())};
        }
        if (Objects.nonNull(tmsLinksAnnotation)) {
            return Arrays.stream(tmsLinksAnnotation.value()).mapToInt(v -> Integer.parseInt(v.value())).toArray();
        }
        return null;
    }

    public static void skipIfJiraItemExist(ITestResult iTestResult) {
        String jiraItem = getJiraItem(iTestResult);
        if (nonNull(jiraItem)) {
            String skippedMsg = "Test skipped due to Jira Item " + jiraItem;
            Logger.log(skippedMsg);
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException(jiraItem);
        }
    }

    public static void runCmdCommand(String processPath) {
        try {
            Runtime rt = Runtime.getRuntime();
            logger.info("Running command " + processPath + " - Waiting for result");
            rt.exec("cmd.exe /c" + processPath);
            logger.info("Command " + processPath + " - Completed Successfully");
        } catch (Exception e) {
            logger.info("Failed to run command.\n" +
                    "Command: " + processPath + "\n" +
                    e.getMessage());
        }
    }

    public static void killProcess(String serviceName) {
        try {
            Runtime.getRuntime().exec(String.format(KILL, serviceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertDtoMapToStringWithReplaceParameter(Map<String, Object> dtoMap, String from, String to) {
        return dtoMap.entrySet().stream().map(r -> r.getKey() + from + r.getValue()).collect(Collectors.joining(to));
    }

    public static String convertObjectToString(Object object) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(object);
            return json;
        } catch (Exception e) {
            logger.info("Could not convert object to map " + e.getMessage());
            return null;
        }
    }

    public static boolean isProcessRunning(String serviceName) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(TASKLIST);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(serviceName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFile(File file) {
        if (nonNull(file)) {
            if (file.delete()) {
                logger.info("file deleted");
            } else {
                logger.info("file is not deleted");
            }
        }
    }

    public static GraphicsDevice getScreenResolution() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd;
    }

    public static int getScreenResolutionWidth() {
        return getScreenResolution().getDisplayMode().getWidth();
    }

    public static int getScreenResolutionHeight() {
        return getScreenResolution().getDisplayMode().getHeight();
    }

    public static Integer getRandomInteger(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static Long getTimestampMillis() {
        return new DateTime().getMillis();
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, false);
    }

    public static String getRandomString() {
        return getRandomString(9);
    }

    public static int getAbsRandomInt() {
        return Math.abs(new Random().nextInt());
    }

    public static Double getRandomDouble() {
        return new Random().nextDouble();
    }

    public static Float getRandomFloat() {
        return new Random().nextFloat();
    }

    public static Integer get8DigitsInteger() {
        return getRandomInteger(99999999, 10000000);
    }

    public static String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    @Nullable
    public static String getFilePathFromResources(Class<?> clazz, String relativePath) {
        URL sourceUrl = clazz.getResource(relativePath);
        String sourceFilePath = null;
        try {
            sourceFilePath = Paths.get(sourceUrl.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            logger.info("Wrong URI syntax: " + e.getMessage());
        }
        return sourceFilePath;
    }

}
