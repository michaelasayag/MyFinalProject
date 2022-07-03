package utilities;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listeners extends CommonOps implements ITestListener {
    public void onFinish(ITestContext test)
    {
            System.out.println("-------------------- Ended Execution ------------------");
    }

    public void onStart(ITestContext test)
    {
            System.out.println("-------------------- Started Execution ------------------");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult test)
    {
            System.out.println("-------------------- Test: " + test.getName() + " Passed Partially ------------------");
    }

    public void onTestFailure(ITestResult test)
    {
        System.out.println("-------------------- Test: " + test.getName() + " Failed ------------------");
        if (!platform.equalsIgnoreCase("api")) {
            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveScreenshot();
        }

    }

    public void onTestSkipped(ITestResult test)
    {
            System.out.println("-------------------- Skipping Test: " + test.getName() +"------------------");
    }

    public void onTestStart(ITestResult test)
    {
            System.out.println("-------------------- Starting Test: " + test.getName() +"------------------");
    }

    public void onTestSuccess(ITestResult test) {
//        Stop Recording
        System.out.println("-------------------- Test: " + test.getName() + " Passed ------------------");
        if (!platform.equalsIgnoreCase("api")) {
            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                e.printStackTrace();
            }
//        Delete Record File
            File file = new File("./test-recordings/" + test.getName() + ".avi");
            if (file.delete())
                System.out.println("file deleted successfully");
            else
                System.out.println("Failed To Delete File");
        }
    }


    @Attachment(value = "Page screen-shot", type = "image/png")
    public  byte[] saveScreenshot(){
        if (!platform.equalsIgnoreCase("mobile"))
        return  ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        else
            return  null;

    }
}

