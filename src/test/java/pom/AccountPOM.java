package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPOM {

    private WebDriver driver;

    public AccountPOM(WebDriver driver) {
        this.driver = driver;
    }

    public By pageTitle = By.cssSelector("div.container:nth-child(3) > app-overview:nth-child(2) > h1:nth-child(1)");

}
