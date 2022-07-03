package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class MainPage {

        @FindBy(how = How.CSS, using = "h1[class='css-1aanzv4']")
        public WebElement head_dashboard;

        @FindBy(how = How.XPATH, using = "//nav[@class='css-1kwy7z0 sidemenu']/ul[2]/li")
        public List<WebElement> list_sidebar;
}
