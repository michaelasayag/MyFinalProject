package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.sikuli.script.Screen;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import workflows.ElectronFlows;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CommonOps extends Base {
//##############################################################################################################
//#######################################*** "getData" method ***###############################################
//##############################################################################################################
//#######This "getData" method receives desired parameter and returns its value from DataConfig.xml file.#######
//#######This file holds common data values like connection parameters or credentials.##########################
//##############################################################################################################

    public static String getData(String nodeName) {
        File fXmlFile;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            fXmlFile = new File("./Configuration/DataConfig.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = (Document) dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Error Reading XML file: " + e);
        } finally {
            return doc.getElementsByTagName(nodeName).item(0).getTextContent();
        }
    }
//##############################################################################################################
//#######################################*** "initBrowser" method ***###########################################
//##############################################################################################################
//########This method is used to start a browser session and maximizes browser's window#########################
//###############(depended on browser type - the method receives desired browser type)##########################
//##############################################################################################################
    public void initBrowser(String BrowserType) {
        if (BrowserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver();
        else if (BrowserType.equalsIgnoreCase("firefox"))
            driver = initFirefoxDriver();
        else if (BrowserType.equalsIgnoreCase("ie"))
            driver = initIEDriver();
        else
            throw new RuntimeException("Invalid Browser Type");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(getData("Timeout")));
        driver.get(getData("url"));
        ManagePages.initGrafana();
        action = new Actions(driver);
    }

//##############################################################################################################
//##################################*** "initChromeDriver" method ***###########################################
//##############################################################################################################
//####This method is used to initiate Chrome WebDriver and returns it to initBrowser method to start session####
//##############################################################################################################
    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

//##############################################################################################################
//#################################*** "initFirefoxDriver" method ***###########################################
//##############################################################################################################
//####This method is used to initiate Firefox WebDriver and returns it to initBrowser method to start session###
//##############################################################################################################
    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

//##############################################################################################################
//#################################*** "initIEDriver" method ***################################################
//##############################################################################################################
//######This method is used to initiate IE WebDriver and returns it to initBrowser method to start session######
//##############################################################################################################
    public static WebDriver initIEDriver() {
        WebDriverManager.iedriver().setup();
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }

//##############################################################################################################
//#################################*** "initElectron" method ***################################################
//##############################################################################################################
//###########This method is used to initiate Electron Driver to work with Electron application##################
//##############################################################################################################
    public static void initElectron(){
        System.setProperty("webdriver.chrome.driver",getData("ElectronDriverPath"));
        ChromeOptions opt = new ChromeOptions();
        opt.setBinary(getData("ElectronAppPath"));
        dc.setCapability("chromeOptions",opt);
        dc.setBrowserName("chrome");
        driver = new ChromeDriver(dc);
        ManagePages.initTodo();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(getData("Timeout")));

    }

//##############################################################################################################
//####################################*** "initMobile" method ***###############################################
//##############################################################################################################
//######################This method is used to initiate mobile WebDriver.#######################################
//###########and sets mobile application to work via device and app parameters from DataConfig.xml file.########
//#######################Mobile Device Connection is capable through "Appium Server GUI/Studio"#################
//##############################################################################################################
    public static void initMobile(){
        dc.setCapability(MobileCapabilityType.NO_RESET,true);
        dc.setCapability(MobileCapabilityType.UDID, getData("UDID"));
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getData("AppPackage"));
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getData("AppActivity"));
        try{
        mobileDriver = new AndroidDriver<>(new URL(getData("AppiumServer")), dc);}
        catch (Exception e) {
            System.out.println("Cannot Connect To Appium Server, See Dwtails:" + e);
        }
        ManagePages.initMortgage();
        mobileDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(mobileDriver,Long.parseLong(getData("Timeout")));
        if  (!platform.equalsIgnoreCase("mobile"))
        action = new Actions(driver);
    }

//##############################################################################################################
//####################################*** "initAPI" method ***##################################################
//##############################################################################################################
//#########This method is used to initiate Web API. RestAssured was selected to work with API.##################
//#########Login credentials and URL to Grafana were taken from DataConfig.xml file#############################
//##############################################################################################################

    public static void initAPI(){
        RestAssured.baseURI = getData("urlAPI");
        httpRequest = RestAssured.given().auth().preemptive().basic(getData("UserName"),getData("Password"));
    }

//##############################################################################################################
//#################################*** "initDesktop" method ***#################################################
//##############################################################################################################
//###########This method is used to initiate Windows Driver to work with Desktop application####################
//##############################################################################################################
    public static void initDesktop(){
        dc.setCapability("app",getData("CalculatorApp"));
        try {
            driver = new WindowsDriver(new URL(getData("DesktopServer")),dc);
        } catch (MalformedURLException e) {
            System.out.println("can not connect to server, see details:" + e);
        }
        ManagePages.initCalculator();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(getData("Timeout")));
    }


//##############################################################################################################
//####################################*** "startSession" method ***#############################################
//##############################################################################################################
//##This method receives Platform parameter (web,api, etc) and then calls another method to initiate its driver#
//##Then it calls to another method to initiate PageObjects (elements on page)##################################
//##Also it calls the method which initiates connection to DB with values taken from DataConfig.xml file########
//##############################################################################################################
    @BeforeClass
    @Parameters({"PlatformName"})
    public void startSession(String PlatformName){
        platform = PlatformName;
        if (platform.equalsIgnoreCase("web"))
            initBrowser(getData("BrowserName"));
        else if  (platform.equalsIgnoreCase("mobile"))
            initMobile();
        else if  (platform.equalsIgnoreCase("api"))
            initAPI();
        else if  (platform.equalsIgnoreCase("electron"))
            initElectron();
        else if  (platform.equalsIgnoreCase("desktop"))
                initDesktop();
        else
            throw new RuntimeException("Invalid platform name");
        softAssert = new SoftAssert();
        screen = new Screen();
        if  (!platform.equalsIgnoreCase("mobile"))
            if (!platform.equalsIgnoreCase("api"))
            {
        action = new Actions(driver);}
        ManageDB.openConnection(getData("DBURL"),getData("DBUserName") ,getData("DBPassword"));
    }

//##############################################################################################################
//#################################*** "afterMethod" method ***#################################################
//##############################################################################################################
//###########This method is used to open Grafana WebPage after each test.#######################################
//###########Using URL from DataConfig.xml file (only in case when working with WebDriver)######################
//###########Also if platform is Electron This Method Clears the list after test case###########################
//##############################################################################################################
    @AfterMethod
    public void afterMethod(){
        if (platform.equalsIgnoreCase("web"))
        driver.get(getData("url"));
        else if (platform.equalsIgnoreCase("electron"))
            ElectronFlows.emptyList();
    }

//##############################################################################################################
//#################################*** "beforeMethod" method ***################################################
//##############################################################################################################
//###########This method is used to start MonteScreenRecorder.##################################################
//###########Only If Platform Name Is Acceptable################################################################
//##############################################################################################################
    @BeforeMethod
    public void beforeMethod(Method method){
        if (!platform.equalsIgnoreCase("api"))
        try {
            MonteScreenRecorder.startRecord(method.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
       if (platform.equalsIgnoreCase("mobile"))
            initMobile();
    }


//##############################################################################################################
//#################################*** "closeSession" method ***################################################
//##############################################################################################################
//#######This method is used to close connection with DB and for ending the session after finishing tests#######
//##############################################################################################################
    @AfterClass
    public void closeSession() {
        ManageDB.closeConnection();
        if (!platform.equalsIgnoreCase("api")) {
            if (!platform.equalsIgnoreCase("mobile"))
                driver.quit();
            else
                mobileDriver.quit();
        }
    }

}
