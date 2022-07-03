package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;
import pageObjects.grafana.AddNewUserPage;
import pageObjects.grafana.EditUserPage;
import pageObjects.grafana.ServerAdminMainPage;
import pageObjects.grafana.ServerAdminMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Base {
    protected static WebDriverWait wait;
    protected static WaitOptions waitOptions;
    protected static WebDriver driver;
    protected static Actions action;
    protected static SoftAssert softAssert;
    protected static Screen screen;
    protected static String platform;


    //    Page objects
    public static pageObjects.grafana.LoginPage grafanaLogin;
    public static pageObjects.grafana.MainPage grafanaMain;
    public static pageObjects.grafana.LeftMenuPage grafanaleftMenuPage;
    public static ServerAdminMenu grafanaServerAdminPage;
    public static ServerAdminMainPage grafanaServerAdminMainPage;
    public static AddNewUserPage grafanaServerAdminAddNewUser;
    public static EditUserPage grafanaServerAdminDeleteUser;

//    Mobile
    protected static pageObjects.mortgage.MainPage mortgageMain;
    protected static AndroidDriver<AndroidElement>  mobileDriver;
    protected static DesiredCapabilities dc = new DesiredCapabilities();

//    Rest API

    protected static RequestSpecification httpRequest;
    protected static Response response;
    protected static JSONObject params = new JSONObject();
    protected static JsonPath jp;

    //    Electron
    protected static pageObjects.todo.MainPage todoMain;

//    Desktop
    protected static pageObjects.calculator.MainPage calcMain;

    //    DataBase
    protected static Connection con;
    protected static Statement stmt;
    protected static ResultSet rs;

}
