
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

@ExtendWith(ScreenShooterExtension.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "firefox");

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = browser ;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                Configuration.browserCapabilities = chromeOptions;
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                Configuration.browserCapabilities = firefoxOptions;
                break;
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                Configuration.browserCapabilities = safariOptions;
                break;
            default:
                // Handle unknown browser
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        Configuration.baseUrl = "https://practicesoftwaretesting.com";
        Selenide.open("/");
        driver = WebDriverRunner.getWebDriver();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
