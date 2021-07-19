package framework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
@Builder
@AllArgsConstructor
public class ConfigProvider {
    private final Properties properties = new Properties();

    private final String publicSiteUrl;
    private final String user;
    private final String password;
    private final String baseTimeout;
    private final String step_logger_enable;

    public ConfigProvider() {
        publicSiteUrl = getConfigParameter("main.url");
        user = getConfigParameter("main.user");
        password = getConfigParameter("main.user.password");
        baseTimeout = getConfigParameter("base.timeout");
        step_logger_enable = getConfigParameter("step.logger.enable");
    }

    public static String getConfigParameter(String propertyName){
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("./src/test/resources/env.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }
}
