package utils;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Log4j
public class Logger {
    private static final String PATTERN = "[%s] %s";
    private String className = "";
    private static String defaultClassName = "Logger";
    private final String CLASS_NAME_SEPARATOR = ".";

    public static void log(String info) {
        String logString = format(PATTERN, defaultClassName, info);
        log.info(logString);
        Allure.step(logString);
    }

    public void info(String object) {
        String logString = String.format(PATTERN, className, object);
        log.info(logString);
        Allure.step(logString);
    }

    public void stepInfo(String object) {
        className = "STEP:";
        String logString = String.format(PATTERN, className, object);
        log.info(logString);
    }
    public void info(String object, Object... args) {
        String logString = String.format(PATTERN, className, format(object, args));
        log.info(logString);
        Allure.step(logString);
    }

    private Logger setClassName(String className) {
        className = getClassName(className);
        this.className = className;
        return this;
    }

    private String getClassName(String name) {
        if (name.contains(CLASS_NAME_SEPARATOR)) {
            List<String> list = Arrays.asList(name.split("\\" + CLASS_NAME_SEPARATOR));
            return list.get(list.size() - 1);
        }
        return name;
    }

    public static Logger get(String name) {
        return new Logger().setClassName(name);
    }

    public static Logger get(Class className) {
        return new Logger().setClassName(className.getName());
    }
}
