package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.sikuli.script.FindFailed;
import utilities.CommonOps;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.FileAssert.fail;

 public class Verifications extends CommonOps {

        @Step("verify Text In Element")
        public static void verifyTextInElement(WebElement elem, String expected) {
            wait.until(ExpectedConditions.visibilityOf(elem));
            assertEquals(elem.getText(), expected);
        }

        @Step("verify Number Of Element")
        public static void numberOfElements(List<WebElement> elems, int expected){
            wait.until(ExpectedConditions.visibilityOf(elems.get(elems.size() -1)));
            assertEquals(elems.size(),expected);
        }

        @Step("verify Visability Of Elements(Soft Assertion")
        public static void visabilityOfElements(List<WebElement> elems){
            for(WebElement elem : elems){
                softAssert.assertTrue(elem.isDisplayed(),"sorry" + elem.getText() + "Is Not Displayed");
            }
            softAssert.assertAll("Some Elements Were Not Displayed");
        }

        @Step("verify Element Visually")
        public static void visualElement (String expectedImageName)
        {
            if (platform.equalsIgnoreCase("web"))
            try {
                screen.find(getData("ImageRepo") + expectedImageName + ".PNG");
            } catch (FindFailed findFailed) {
                System.out.println("Error Comparing Image File" + findFailed);
                fail("Error Comparing Image File" + findFailed);
            }

        }

        @Step("Verify Element Displayed")
        public static void exsistanceOfElement(List<WebElement> elements){
            assertTrue(elements.size() > 0);
        }

        @Step("Verify Element Not Displayed")
        public static void nonExsistanceOfElement(List<WebElement> elements){
            assertFalse(elements.size() > 0);
        }

        @Step("Verify Text With Text")
        public static void verifyText(String actual, String expected){
            assertEquals(actual,expected);
        }

     @Step("Verify number With number")
     public static void verifyNumber(int actual, int expected){
         assertEquals(actual,expected);
     }




    }


