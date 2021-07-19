package enums;

import interfaces.StringMethods;

public enum WebEnvProperties implements StringMethods
{
    ENV_URL("env.url"),
    BROWSER("browser"),
    MAIN_USER("main.user"),
    MAIN_USER_PASSWORD("main.user.password");

    private String name;

    WebEnvProperties(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return System.getProperty(this.toString());
    }

    public void setValue(String value)
    {
        System.setProperty(this.name(), value);
    }
}