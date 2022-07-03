package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.grafana.AddNewUserPage;
import pageObjects.grafana.EditUserPage;
import pageObjects.grafana.ServerAdminMainPage;
import pageObjects.grafana.ServerAdminMenu;



public class ManagePages extends Base {
    public static void initGrafana() {
        grafanaLogin = PageFactory.initElements(driver, pageObjects.grafana.LoginPage.class);
        grafanaMain = PageFactory.initElements(driver, pageObjects.grafana.MainPage.class);
        grafanaleftMenuPage = PageFactory.initElements(driver, pageObjects.grafana.LeftMenuPage.class);
        grafanaServerAdminPage = PageFactory.initElements(driver, ServerAdminMenu.class);
        grafanaServerAdminMainPage = PageFactory.initElements(driver, ServerAdminMainPage.class);
        grafanaServerAdminAddNewUser = PageFactory.initElements(driver, AddNewUserPage.class);
        grafanaServerAdminDeleteUser = PageFactory.initElements(driver, EditUserPage.class);
    }

    public static void initMortgage(){
        mortgageMain = new pageObjects.mortgage.MainPage(mobileDriver);
    }

    public static void initTodo(){
        todoMain = PageFactory.initElements(driver, pageObjects.todo.MainPage.class);
    }

    public static void initCalculator(){
        calcMain = PageFactory.initElements(driver, pageObjects.calculator.MainPage.class);
    }
}
