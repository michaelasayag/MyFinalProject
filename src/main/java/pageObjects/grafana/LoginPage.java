package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    @FindBy(how = How.NAME, using = "user")
    public WebElement txt_username;

    @FindBy(how = How.NAME, using = "password")
    public WebElement txt_password;

    @FindBy(how = How.CLASS_NAME, using = "css-14g7ilz-button")
    public WebElement btn_login;

    @FindBy(how = How.XPATH, using = "//button[@class='css-1kf0ycc-button']")
    public WebElement btn_skip;

}
