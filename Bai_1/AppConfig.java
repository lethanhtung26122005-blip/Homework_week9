package Homework.Homework_week9.Bai_1;

public class AppConfig {
    private static AppConfig instance = null;
    private String appName;
    private String version;
    private String logLevel;

    private AppConfig() {
        this.appName = "MyApp";
        this.version = "1.0.0";
        this.logLevel = "INFO";
    }
    public static AppConfig getInstance() {
        if(instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
}
