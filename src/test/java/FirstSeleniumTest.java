import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.AccountPOM;
import pom.ContactPagePOM;
import pom.LoginPagePOM;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstSeleniumTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 0)
    public void testLoginWithMultipleUsers(String username, String password){
        Selenide.open("/auth/login");

        By firstPageTitle = By.cssSelector(".col-lg-6 > h3:nth-child(1)");

        WebDriverWait waitTitle = new WebDriverWait(driver, Duration.ofSeconds(8));
        waitTitle.until(
                ExpectedConditions.visibilityOfElementLocated(firstPageTitle)
        );

        LoginPagePOM loginPagePOM = new LoginPagePOM(driver);
        AccountPOM accountPOM = new AccountPOM(driver);

        //Login user
        loginPagePOM.fillForm(username, password);

        //submit form https://practicesoftwaretesting.com/account
        loginPagePOM.submitForm();

        //wait for the success alert to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountPOM.pageTitle)
        );
        String pageTitle = alert.getText().trim();

        assertEquals("My account", pageTitle);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/form-data.csv", numLinesToSkip = 0)
    public void testContactFormSubmit(String firstName, String lastName, String email, String subject, String message){
        Selenide.open("/contact");

        By pageTitle = By.cssSelector("body > app-root > div.container > app-contact > div > div > div > h3");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        );

        ContactPagePOM contactPagePOM = new ContactPagePOM(driver);

        //fillForm
        contactPagePOM.fillForm(firstName, lastName, email, subject, message);

        //submit form
        contactPagePOM.submitForm();

        //wait for the success alert to appear
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(contactPagePOM.alertLoc)
        );
        String alertText = alert.getText().trim();

        assertEquals("Thanks for your message! We will contact you shortly.", alertText);

    }

}
