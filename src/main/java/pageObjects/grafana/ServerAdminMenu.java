package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ServerAdminMenu {

    @FindBy(how = How.XPATH, using = "//ul[@class='css-19yi9cf']/li[2]")
    public WebElement link_users;

    @FindBy(how = How.XPATH, using = "//ul[@class='css-19yi9cf']/li[3]")
    public WebElement link_orgs;

    @FindBy(how = How.XPATH, using = "//ul[@class='css-19yi9cf']/li[4]")
    public WebElement link_settings;

    @FindBy(how = How.XPATH, using = "//ul[@class='css-19yi9cf']/li[5]")
    public WebElement link_plugins;

    @FindBy(how = How.XPATH, using = "//ul[@class='css-19yi9cf']/li[6]")
    public WebElement link_stats_n_licensing;
}
