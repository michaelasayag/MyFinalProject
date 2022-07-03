package pageObjects.mortgage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;


public class MainPage {
    private AndroidDriver<AndroidElement> mobileDriver;




    public MainPage(AndroidDriver<AndroidElement> mobileDriver){
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver,Duration.ofSeconds(20)), this);
    }

    @AndroidFindBy(id = "etAmount")
    public static   AndroidElement txt_amount;

    @AndroidFindBy(id = "etTerm")
    public static   AndroidElement txt_term;

    @AndroidFindBy(id = "etRate")
    public static   AndroidElement txt_rate;

    @AndroidFindBy(id = "add_schedule_text")
    public static   AndroidElement btn_calculate;

    @AndroidFindBy(id = "tvRepayment")
    public static   AndroidElement txt_repayment;
}
