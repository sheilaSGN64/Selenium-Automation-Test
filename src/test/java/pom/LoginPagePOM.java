package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPagePOM {

    private WebDriver driver;
    public LoginPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    By emailLoc = By.id("email");
    By passwordLoc = By.id("password");
    By btnSubmitLoc = By.className("btnSubmit");

    By alertLoc = By.className("alert");

    public void fillForm(String username, String password) {
        driver.findElement(emailLoc).sendKeys(username);
        driver.findElement(passwordLoc).sendKeys(password);

    }

    public void submitForm() {
        driver.findElement(btnSubmitLoc).click();
    }


}
