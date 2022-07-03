package workflows;

import extensions.DBActions;
import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;

import utilities.CommonOps;

import java.util.List;


public class WebFlows extends CommonOps {

    @Step("Business Flow: Login to Grafana With DB Credentials")
    public static void loginDB(){
        String query = "SELECT username, password FROM Employees WHERE id='3'";
        List<String> cred = DBActions.getCredentials(query);
        UIActions.updateText(grafanaLogin.txt_username, cred.get(0));
        UIActions.updateText(grafanaLogin.txt_password, cred.get(1));
        UIActions.click(grafanaLogin.btn_login);
        UIActions.click(grafanaLogin.btn_skip);
    }

    @Step("Business Flow: Login")
    public static void login(String user, String pass) {
        UIActions.updateText(grafanaLogin.txt_username, user);
        UIActions.updateText(grafanaLogin.txt_password, pass);
        UIActions.click(grafanaLogin.btn_login);
        UIActions.click(grafanaLogin.btn_skip);



    }
    @Step("Business Flow: Create New User")
    public static void createNewUser(String name, String email, String userName, String Password) {
        UIActions.click(grafanaServerAdminMainPage.btn_newUser);
        UIActions.updateText(grafanaServerAdminAddNewUser.txt_name, name);
        UIActions.updateText(grafanaServerAdminAddNewUser.txt_email, email);
        UIActions.updateText(grafanaServerAdminAddNewUser.txt_username, userName);
        UIActions.updateText(grafanaServerAdminAddNewUser.txt_password, Password);
        UIActions.click(grafanaServerAdminAddNewUser.btn_create);

    }

    @Step("Business Flow: Delete Last User")
    public static void deleteLastuser(){
        UIActions.click(grafanaServerAdminMainPage.userlist_table.get(grafanaServerAdminMainPage.userlist_table.size()-1));
        UIActions.click(grafanaServerAdminDeleteUser.btn_deleteuser);
        UIActions.click(grafanaServerAdminDeleteUser.btn_deleteuserconfirm);

    }

    @Step("Business Flow: Search And Verify User")
    public static void searchAndVerifyUser(String user, String shouldExist){
        UIActions.updateTextHuman(grafanaServerAdminMainPage.txt_search, user);
        if (shouldExist.equalsIgnoreCase("exists"))
            Verifications.exsistanceOfElement(grafanaServerAdminMainPage.userlist_table);
        else if (shouldExist.equalsIgnoreCase("not-exist"))
            Verifications.nonExsistanceOfElement(grafanaServerAdminMainPage.userlist_table);
        else
            throw new RuntimeException("Invalid Expected Output Option in Data Driven Testing, Should be exist or not-exists ");
    }
}

