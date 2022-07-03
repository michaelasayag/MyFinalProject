package sanity;


import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.WebFlows;

import javax.swing.text.Utilities;

@Listeners({utilities.Listeners.class})
public class GrafanaWeb extends CommonOps {

    @Test(description = "Test01 - Verify Header")
    @Description("This Test Login And Verify The Main Header")
    public void test01_verifyHeader(){
        WebFlows.login(getData("UserName"), getData("Password"));
        Verifications.verifyTextInElement(grafanaMain.head_dashboard,"Welcome to Grafana");

    }

    @Test(description = "Test02 - Verify User Page")
    @Description("This Test Verify Users On User Page")
    public void test02_verifyUserPage(){
        UIActions.mouseHover(grafanaleftMenuPage.btn_server, grafanaServerAdminPage.link_users);
        Verifications.numberOfElements(grafanaServerAdminMainPage.userlist_table,1);

    }

    @Test(description = "Test03 - Verify User Added")
    @Description("This Test Verify User Added Successfully")
    public void test03_verifyUserAdded(){
        UIActions.mouseHover(grafanaleftMenuPage.btn_server, grafanaServerAdminPage.link_users);
        WebFlows.createNewUser("Michael", "m.asyag@gmail.com", "masyag", "123456");
        Verifications.numberOfElements(grafanaServerAdminMainPage.userlist_table,2);
    }

    @Test(description = "Test04 - Verify User Deleted")
    @Description("This Test Verify Last User Deleted Successfully")
    public void test04_verifyUserDeleted() throws InterruptedException {
        UIActions.mouseHover(grafanaleftMenuPage.btn_server, grafanaServerAdminPage.link_users);
        WebFlows.deleteLastuser();
        Thread.sleep(500);
        Verifications.numberOfElements(grafanaServerAdminMainPage.userlist_table,1);
    }

    @Test(description = "Test05 - Verify SideBar Elements Are Visible")
    @Description("This Test Verify All Side Bar Elements Are Visible (using soft assertion)")
    public void test05_verifySideBar(){
        Verifications.visabilityOfElements(grafanaMain.list_sidebar);
    }

    @Test(description = "Test06 - Verify Admin Avatar Visually")
    @Description("This Test Verify Admin Avatar Image Using Sikuli tool")
    public void test06_verifyAdminAvatar(){
        Verifications.visualElement("adminavatar");
    }

    @Test(description = "Test07 - Search Users", dataProvider = "data-provider-users", dataProviderClass = utilities.ManageDDT.class)
    @Description("This Test Search Users in table using DDT")
    public void test07_searchUsers(String user, String shouldExist){
        UIActions.mouseHover(grafanaleftMenuPage.btn_server, grafanaServerAdminPage.link_users);
        WebFlows.searchAndVerifyUser(user,shouldExist);
    }

}
