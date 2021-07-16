package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static enums.EnvProperties.*;
import static java.util.Objects.nonNull;

public class Utils
{
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

    public static String setQuestions()
    {
        return "{\"questionsDetails\" : [{\"id\": \"10001000-0000-0000-0000-000000000000\",\"question\": \"Have you had a fever over 38º?\",\"predefinedAnswers\": [\"<38\",\">38\"]},{\"id\": \"20001000-0000-0000-0000-000000000000\",\"question\": \"Do you have a dry cough?\",\"predefinedAnswers\": [\"Yes\",\"No\"]},{\"id\": \"30001000-0000-0000-0000-000000000000\",\"question\": \"Have you had shortness of breath?\",\"predefinedAnswers\": [\"Yes, yesterday\",\"Yes, last week\",\"Yes, last month\",\"No, never\"]},{\"id\": \"40001000-0000-0000-0000-000000000000\",\"question\": \"Have you been abroad in the last month?\",\"predefinedAnswers\": [\"Asia\",\"Europe\",\"Other\",\"No\"]},{\"id\": \"50001000-0000-0000-0000-000000000000\",\"question\": \"How do you feel in general?\",\"predefinedAnswers\": [\"Good\",\"Not great\",\"Dreadful\"]},{\"id\": \"60001000-0000-0000-0000-000000000000\",\"question\": \"¿Ha presentado fiebre?\",\"predefinedAnswers\": [\"<38\",\">38\"]},{\"id\": \"70001000-0000-0000-0000-000000000000\",\"question\": \"¿Tiene tos seca?\",\"predefinedAnswers\": [\"Si\",\"No\"]},{\"id\": \"80001000-0000-0000-0000-000000000000\",\"question\": \"¿Has tenido problemas para respirar recientemente?\",\"predefinedAnswers\": [\"Si, ayer\",\"Si, la semana pasada\",\"Si, el mes passado\",\"No, nunca\"]},{\"id\": \"90001000-0000-0000-0000-000000000000\",\"question\": \"¿Ha realizado un viaje al extranjero en el último mes?\",\"predefinedAnswers\": [\"Asia\",\"Europa\",\"Otro\",\"No\"]},{\"id\": \"10001000-0000-0000-0000-000000000000\",\"question\": \"Cómo se siente en general?\",\"predefinedAnswers\": [\"Bien\",\"Más o menos\",\"Terrible\"]},{\"id\": \"11001000-0000-0000-0000-000000000000\",\"question\": \"Você já teve febre acima de 38º?\",\"predefinedAnswers\": [\"<38\",\">38\"]},{\"id\": \"12001000-0000-0000-0000-000000000000\",\"question\": \"Você tem tosse seca?\",\"predefinedAnswers\": [\"Sim\",\"Não\"]},{\"id\": \"13001000-0000-0000-0000-000000000000\",\"question\": \"Você já sentiu falta de ar ultimamente?\",\"predefinedAnswers\": [\"Sim, ontem\",\"Sim, na semana anterior\",\"Sim, no mês passado\",\"Não, nunca\"]},{\"id\": \"14001000-0000-0000-0000-000000000000\",\"question\": \"Você fez uma viagem ao exterior no último mês?\",\"predefinedAnswers\": [\"Ásia\",\"Europa\",\"Outro\",\"Não\"]},{\"id\": \"15001000-0000-0000-0000-000000000000\",\"question\": \"Como você se sente em geral?\",\"predefinedAnswers\": [\"Bem\",\"Mais ou menos\",\"Terrível\"]},{\"id\": \"16001000-0000-0000-0000-000000000000\",\"question\": \"Avez-vous eu une fièvre supérieure à 38°?\",\"predefinedAnswers\": [\"<38\",\">38\"]},{\"id\": \"17001000-0000-0000-0000-000000000000\",\"question\": \"Avez-vous une toux sèche?\",\"predefinedAnswers\": [\"Oui\",\"Non\"]},{\"id\": \"18001000-0000-0000-0000-000000000000\",\"question\": \"Vous avez récemment eu du mal à respirer?\",\"predefinedAnswers\": [\"Oui, hier\",\"Oui, la semaine dernière\",\"Oui, le mois dernier\",\"Non, jamais\"]},{\"id\": \"19001000-0000-0000-0000-000000000000\",\"question\": \"Avez-vous été à l'étranger au cours du dernier mois?\",\"predefinedAnswers\": [\"Asie\",\"L'Europe\",\"Autre\",\"Non\"]},{\"id\": \"20001000-0000-0000-0000-000000000000\",\"question\": \"Comment vous sentez-vous en général?\",\"predefinedAnswers\": [\"Bon\",\"Pas si bon\",\"Terrible\"]},{\"id\": \"21001000-0000-0000-0000-000000000000\",\"question\": \"У вас була температура понад 38°?\",\"predefinedAnswers\": [\"<38\",\">38\"]},{\"id\": \"22001000-0000-0000-0000-000000000000\",\"question\": \"У вас сухий кашель?\",\"predefinedAnswers\": [\"Так\",\"Немає\"]},{\"id\": \"23001000-0000-0000-0000-000000000000\",\"question\": \"Нещодавно у вас виникли проблеми з диханням?\",\"predefinedAnswers\": [\"Так, вчора\",\"Так, минулого тижня\",\"Так, минулого місяця\",\"Ні, ніколи\"]},{\"id\": \"24001000-0000-0000-0000-000000000000\",\"question\": \"Ви здійснили поїздку за кордон за останній місяць?\",\"predefinedAnswers\": [\"Азії\",\"Європа\",\"Інші\",\"Немає\"]},{\"id\": \"25001000-0000-0000-0000-000000000000\",\"question\": \"Як ти себе почуваєш загалом?\",\"predefinedAnswers\": [\"Добре\",\"Більш-менш\",\"Болісно\"]},{\"id\": \"26001000-0000-0000-0000-000000000000\",\"question\": \"האם סבלת מחום של למעלה מ38º?\",\"predefinedAnswers\": [\"<38\",\">38\"]},{\"id\": \"27001000-0000-0000-0000-000000000000\",\"question\": \"האם אתה סובל משיעול ויובש בגרון?\",\"predefinedAnswers\": [\"כן\",\"לא\"]},{\"id\": \"28001000-0000-0000-0000-000000000000\",\"question\": \"האם חשת לאחרונה קוצר נשימה?\",\"predefinedAnswers\": [\"כן, אתמול\",\"כן, בשבוע שעבר\",\"כן, בחודש שעבר\",\"מעולם לא\"]},{\"id\": \"29001000-0000-0000-0000-000000000000\",\"question\": \"האם שהית בחוץ לארץ בחודש האחרון?\",\"predefinedAnswers\": [\"אסיה\",\"אירופה\",\"אחר\",\"לא\"]},{\"id\": \"30001000-0000-0000-0000-000000000000\",\"question\": \"איך אתה מרגיש באופן כללי?\",\"predefinedAnswers\": [\"טוב\",\"לא משהו\",\"זוועה\"]}]}";
    }

    public static String setReportDataSharingPerCNCFeatures()
    {
        return "{\"hideCallerPhoneNumberFeature\": \"" + "ON" + "\", \"reportIdentificationPresentation\": \"" + "CALLER_PHONE_NUMBER" + "\"}";
    }

    public static String setReportDataSharingPerOperatorFeatures()
    {
        return "{\"accessPermissions\": \"" + "FULL_ACCESS" + "\"}";
    }

    public static String prettyPrint(Object obj)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonParser jp = new JsonParser();
        JsonElement je;
        je = jp.parse(gson.toJson(obj));
        return gson.toJson(je);
    }

    public static String prettyPrint(String obj)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je;
        try {
            je = jp.parse(obj);
        } catch (Exception e) {
            je = jp.parse(gson.toJson(obj));
        }
        return gson.toJson(je);
    }

    public static <T> T parseJson(String json, Class<T> clazz)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, clazz);
    }

    public static <T> T parseJson(String json, TypeToken<T> clazz)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, clazz.getType());
    }

    public static String parseJsonFile(String filePath, String key)
    {
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

    public static <T> T parseJsonFile(String filePath, Class<T> clazz)
    {
        JSONParser parser = new JSONParser();
        T value = (T) "";
        try {
            FileInputStream fisTargetFile = new FileInputStream(new File(filePath));
            String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");
            value = parseJson(targetFileStr, clazz);

        } catch (Exception e) {
            e.getMessage();
        }
        return value;
    }

    public static String convertToUTF8(String text)
    {
        return new String(text.getBytes(StandardCharsets.UTF_8));
    }

    public static void editJsonFile(String filePath, String key, String value)
    {
        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject);
            String currentValue = (String) jsonObject.get(key);
            jsonObject.put(key, currentValue + " " + value);

            FileWriter writer = new FileWriter(filePath);
            writer.write(jsonObject.toJSONString());

            FileOutputStream outputStream = new FileOutputStream(filePath);
            byte[] strToBytes = jsonObject.toString().getBytes();
            outputStream.write(strToBytes);

        } catch (ParseException | IOException e) {
            e.getMessage();
        }
    }

    public static String decorateInfoString(String infoString)
    {
        StringBuilder builder = new StringBuilder();
        int infoStringLength = infoString.split("\n")[0].length();
        for (int i = 0; i < infoStringLength; i++) {
            builder.append("=");
        }
        builder.append("\n");
        builder.append(infoString);
        builder.append("\n");
        for (int i = 0; i < infoStringLength; i++) {
            builder.append("=");
        }
        return builder.toString();
    }

    public static String getTestRailSuiteName(Class<?> clazz)
    {
        Epic annotation = clazz.getAnnotation(Epic.class);
        if (nonNull(annotation)) {
            return annotation.value();
        } else {
            return null;
        }
    }

    public static void waitFor(Integer timeInMillis, boolean withLog)
    {
        if (withLog) Logger.log("Waiting for " + timeInMillis + " millis.");
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForThreadsToFinish(Thread... threads)
    {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean isSessionAlive(WebDriver driver)
    {
        try {
            driver.getPageSource();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitFor(Integer timeInMillis)
    {
        waitFor(timeInMillis, true);
    }

    public static void deleteDirectory(String path)
    {
        logger.info("Trying to delete " + path);
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            logger.info("Failed to delete " + path + " with error: " + e.getMessage());
        }
    }

    public static String getLastFolderFromDirectory(String path)
    {
        Path parentFolder = Paths.get(path);
        Optional<File> mostRecentFolder =
                Arrays
                        .stream(parentFolder.toFile().listFiles())
                        .filter(f -> f.isDirectory())
                        .max(Comparator.comparingLong(File::lastModified));
        if (mostRecentFolder.isPresent()) {
            File mostRecent = mostRecentFolder.get();
            return mostRecent.getPath().substring(mostRecent.getPath().lastIndexOf("\\") + 1);
        } else {
            logger.info("folder is empty!");
            return "";
        }
    }

    public static String getOlderVersionFolderFromDirectory(String path, String version)
    {
        Path parentFolder = Paths.get(path);
        List<Object> folderObjectList =
                Arrays.asList(Arrays
                        .stream(parentFolder.toFile().listFiles())
                        .filter(f -> f.isDirectory())
                        .sorted((f1, f2) -> Long.compare(f1.lastModified(),
                                f2.lastModified())).toArray());
        List<File> folderFileList = new ArrayList<>();
        for (int i = 0; i < folderObjectList.size() - 1; i++) {
            File file = (File) folderObjectList.get(i);
            if (!file.getPath().substring(file.getPath().lastIndexOf("\\") + 1).equals(version))
                folderFileList.add((File) folderObjectList.get(i));
            else
                break;
        }
        File oneFromLastRecentFolder = folderFileList.get(folderFileList.size() - 2);
        return oneFromLastRecentFolder.getPath().substring(oneFromLastRecentFolder.getPath().lastIndexOf("\\") + 1);
    }

    public static boolean checkFolderNameContains(String path, String text)
    {
        Path parentFolder = Paths.get(path);
        List<Object> folderObjectList =
                Arrays.asList(Arrays
                        .stream(parentFolder.toFile().listFiles())
                        .filter(f -> f.isDirectory())
                        .sorted((f1, f2) -> Long.compare(f1.lastModified(),
                                f2.lastModified())).toArray());
        for (int i = 0; i < folderObjectList.size(); i++) {
            File file = (File) folderObjectList.get(i);
            if (file.getPath().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getAllFilesInDirectory(String path)
    {
        List<String> results = new ArrayList<String>();
        try {
            File[] files = new File(path).listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getName());
                }
            }
            return results;
        } catch (Exception e) {
            Logger.log("Cannot fond path path: " + path);
            return results;
        }
    }

    public static boolean doesListContainPartOfString(List<String> files, String searchFor)
    {
        for (String file : files) {
            if (file.contains(searchFor))
                return true;
        }
        return false;
    }

    public static List<String> getAllFoldersInDirectory(String path)
    {
        File file = new File(path);
        return Arrays.asList(file.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File current, String name)
            {
                return new File(current, name).isDirectory();
            }
        }));
    }

    public static String getFileNameContainingString(List<String> list, String searchFor)
    {
        for (String s : list) {
            if (s.contains(searchFor))
                return s;
        }
        return "";
    }

    public static boolean changeFileName(String source, String dest)
    {
        File sourceFile = new File(source);
        File destFile = new File(dest);

        if (sourceFile.renameTo(destFile)) {
            Logger.log("File renamed successfully");
            return true;
        } else {
            Logger.log("Failed to rename file");
            return false;
        }
    }

    public static String getPropertyEnv()
    {
        String env = nonNull(TEST_ENV.getValue()) ? TEST_ENV.getValue() : TEST_ENV.getName();
        if (env.contains("qa"))
            return "qa";
        else if (env.contains("dev"))
            return "dev";
        else
            return env.equals("") ? DEFAULT_ENV : env;
    }

    public static String getEnv()
    {
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

    public static String getServicesEnv()
    {
        return nonNull(SERVICES_ENV.getValue()) ? SERVICES_ENV.getValue() : SERVICES_ENV.getName();
    }

    public static String getWebEnv()
    {
        return nonNull(WEB_ENV.getValue()) ? WEB_ENV.getValue() : WEB_ENV.getName();
    }

    public static String getJiraItem(ITestResult iTestResult)
    {
        Issue jiraItem = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Issue.class);

        if (Objects.isNull(jiraItem)) {
            return null;
        } else {
            return jiraItem.value();
        }
    }

    public static int[] getTestRailIds(ITestResult iTestResult)
    {
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

    public static void skipIfJiraItemExist(ITestResult iTestResult)
    {
        String jiraItem = getJiraItem(iTestResult);
        if (nonNull(jiraItem)) {
            String skippedMsg = "Test skipped due to Jira Item " + jiraItem;
            Logger.log(skippedMsg);
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException(jiraItem);
        }
    }

    public static void runCmdCommand(String processPath)
    {
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

    public static String runCommandGetOutput(String command)
    {
        String line;
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            StringBuilder commandOutput = new StringBuilder();
            while ((line = input.readLine()) != null) {
                commandOutput.append(line);
                commandOutput.append('\n');
            }
            BufferedReader input2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            while ((line = input2.readLine()) != null) {
                commandOutput.append(line);
                commandOutput.append('\n');
            }
            pr.waitFor();
            return commandOutput.toString();
        } catch (Exception e) {
            logger.info("Failed to run command.\n" +
                    "Command: " + command + "\n" +
                    e.getMessage());
            return "Unable to get result output from command " + command;
        }
    }

    public static void replaceFileText(String filePath, String search, String replace)
    {
        try {
            FileReader fr = new FileReader(filePath);
            String line;
            String newFile = "";
            try (BufferedReader br = new BufferedReader(fr)) {

                while ((line = br.readLine()) != null) {
                    newFile += line;
                }
                newFile = newFile.replace(search, replace);
                FileWriter fw = new FileWriter(filePath);
                fw.write(newFile);
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void killProcess(String serviceName)
    {
        try {
            Runtime.getRuntime().exec(String.format(KILL, serviceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertDtoMapToStringWithReplaceParameter(Map<String, Object> dtoMap, String from, String to)
    {
        return dtoMap.entrySet().stream().map(r -> r.getKey() + from + r.getValue()).collect(Collectors.joining(to));
    }

    public static String convertObjectToString(Object object)
    {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(object);
            return json;
        } catch (Exception e) {
            logger.info("Could not convert object to map " + e.getMessage());
            return null;
        }
    }

    public static boolean isProcessRunning(String serviceName)
    {
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

    public static void deleteFile(File file)
    {
        if (nonNull(file)) {
            if (file.delete()) {
                logger.info("file deleted");
            } else {
                logger.info("file is not deleted");
            }
        }
    }

    public static GraphicsDevice getScreenResolution()
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd;
    }

    public static int getScreenResolutionWidth()
    {
        return getScreenResolution().getDisplayMode().getWidth();
    }

    public static int getScreenResolutionHeight()
    {
        return getScreenResolution().getDisplayMode().getHeight();
    }

    public static boolean isScreenshotExist(String imagePath)
    {
        try {
            Screen screen = new Screen();
            screen.wait(new Pattern(imagePath).similar(0.8f), 3);
            return true;
        } catch (Exception e) {
            logger.info("Could not find screenshot");
            return false;
        }
    }

    public static boolean isScreenshotExist(String imagePath, float similarityPercent)
    {
        try {
            Screen screen = new Screen();
            screen.wait(new Pattern(imagePath).similar(similarityPercent), 2);
            return true;
        } catch (Exception e) {
            logger.info("Could not find screenshot");
            return false;
        }
    }

    public static boolean waitForScreenShotChange(String imagePath)
    {
        try {
            Screen screen = new Screen();
            screen.waitVanish(new Pattern(imagePath).similar(0.9f), 10);
            return true;
        } catch (Exception e) {
            logger.info("Could not find screenshot");
            return false;
        }
    }

    public static boolean isScreenshotExist(Pattern image)
    {
        try {
            Screen screen = new Screen();
            screen.wait(image.similar(0.6f), 2);
            return true;
        } catch (Exception e) {
            logger.info("Could not find screenshot");
            return false;
        }
    }

    public static void clickOnImage(Pattern image)
    {
        try {
            Screen screen = new Screen();
            screen.wait(image.similar(0.7f), 2).click();
        } catch (Exception e) {
            Assert.fail("Could not find screenshot");
        }
    }

    public static String getClipboardCopy()
    {
        String data = "";
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void setClipboard(String text)
    {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public static Integer getRandomInteger(int max, int min)
    {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static Long getTimestampMillis()
    {
        return new DateTime().getMillis();
    }

    public static String getRandomString(int length)
    {
        return RandomStringUtils.random(length, true, false);
    }

    public static String getRandomString()
    {
        return getRandomString(9);
    }

    public static int getAbsRandomInt()
    {
        return Math.abs(new Random().nextInt());
    }

    public static Double getRandomDouble()
    {
        return new Random().nextDouble();
    }

    public static Float getRandomFloat()
    {
        return new Random().nextFloat();
    }

    public static Integer get8DigitsInteger()
    {
        return getRandomInteger(99999999, 10000000);
    }

    public static String decodeBase64(String encodedString)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    public static void copyFolder(String src, String dst)
    {
        File srcDir = new File(src);
        File destDir = new File(dst);

        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void createDirectory(String folderName)
    {
        File theDir = new File(folderName);
        if (!theDir.exists()) {
            Logger.log("creating directory: " + theDir.getName());

            try {
                if (theDir.mkdirs())
                    Logger.log(folderName + " folder created");
            } catch (Exception e) {
                Logger.log("Failed creating directory: " + theDir.getName() + " with Error: " + e.getMessage());
            }
        }
    }

    @Nullable
    public static String getFilePathFromResources(Class<?> clazz, String relativePath)
    {
        URL sourceUrl = clazz.getResource(relativePath);
        String sourceFilePath = null;
        try {
            sourceFilePath = Paths.get(sourceUrl.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            logger.info("Wrong URI syntax: " + e.getMessage());
        }
        return sourceFilePath;
    }

    /**
     * Retry action for given number of intervals and waiting time of timeToWaitMilliseconds for each interval
     *
     * @param timeToWaitMilliseconds - number of milliseconds to wait for each interval
     * @param interval               - number of retries
     * @param action                 - the action to try to complete
     * @param <R>                    - return value of the action
     * @return R if action returned R before timeout, otherwise returns null
     */

    @Nonnull
    public static <R> Optional<R> retryUntilReturns(int timeToWaitMilliseconds, int interval, Supplier<Optional<R>> action)
    {
        Optional<R> res;
        for (int i = 0; i < interval; i++) {
            if ((res = action.get()).isPresent()) return res;
            waitFor(timeToWaitMilliseconds);
        }
        return Optional.empty();
    }

    @Nonnull
    public static <R> Optional<R> retryUntilReturns(Supplier<Optional<R>> action)
    {
        return retryUntilReturns(DEFAULT_RETRY_ACTION_INTERVAL_MILLIS, DEFAULT_NUMBER_OF_RETRIES, action);
    }

    @Nullable
    public static <R> R retryUntilReturnsWithoutFail(int timeToWaitMilliseconds, int interval, Supplier<Optional<R>> action)
    {
        Optional<R> r = retryUntilReturns(timeToWaitMilliseconds, interval, action);
        return r.orElse(null);
    }

    @Nonnull
    public static <R> R retryUntilReturnsOrElseFail(String failMessage, int timeToWaitMilliseconds, int interval, Supplier<Optional<R>> action)
    {
        Optional<R> r = retryUntilReturns(timeToWaitMilliseconds, interval, action);
        Assert.assertTrue(r.isPresent(), failMessage);
        return r.get();
    }

    @Nonnull
    public static <R> R retryUntilReturnsOrElseFail(String failMessage, Supplier<Optional<R>> action)
    {
        return retryUntilReturnsOrElseFail(failMessage, DEFAULT_RETRY_ACTION_INTERVAL_MILLIS, DEFAULT_NUMBER_OF_RETRIES, action);
    }

    @Nonnull
    public static Boolean retryUntilTrue(int timeToWaitMilliseconds, int interval, Supplier<Boolean> action)
    {
        Boolean res;
        for (int i = 0; i < interval; i++) {
            if ((res = action.get())) return res;
            waitFor(timeToWaitMilliseconds);
        }
        return null;
    }

    @Nonnull
    public static Boolean retryUntilTrue(Supplier<Boolean> action)
    {
        return retryUntilTrue(DEFAULT_RETRY_ACTION_INTERVAL_MILLIS, DEFAULT_NUMBER_OF_RETRIES, action);
    }

    @Nonnull
    public static <R> Optional<R> tryCatch(Supplier<Optional<R>> action, Function<Exception, Optional<R>> catchAction)
    {
        Optional<R> returnValue;
        try {
            returnValue = action.get();
        } catch (Exception e) {
            returnValue = catchAction.apply(e);
        }
        return returnValue;
    }

    @Nonnull
    public static <R> Optional<R> tryCatchThrowException(Supplier<R> action)
    {
        Optional<R> returnValue;
        try {
            returnValue = Optional.of(action.get());
        } catch (Exception e) {
            throw e;
        }
        return returnValue;
    }

    @Nonnull
    public static <R> Optional<R> tryCatchIgnoreException(Supplier<Optional<R>> action)
    {
        return tryCatch(action, unused -> Optional.empty());
    }

    @Nonnull
    public static <R> Optional<R> tryCatchLogException(Supplier<Optional<R>> action)
    {
        return tryCatch(action, e -> {
            Logger.log(e.getMessage());
            return Optional.empty();
        });
    }

    @Nonnull
    public static Optional<Boolean> tryCatchLogException(Runnable action)
    {
        return tryCatchLogException(() ->
                {
                    action.run();
                    return Optional.of(true);
                }
        );
    }

    public static Process startProcess(String command, String path)
    {
        logger.info("Running command: " + command + "\nin " + path);
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"" + path + "\" && " + command);
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            logger.info("Failed to start process with error: " + e.getMessage());
        }
        return p;
    }

    public static void waitForAndDestroyProcess(Process p)
    {
        try {
            p.waitFor();
            p.destroyForcibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startProcessAndWaitForAndDestroy(String command, String path)
    {
        Process p = startProcess(command, path);
        waitForAndDestroyProcess(p);
    }
}
