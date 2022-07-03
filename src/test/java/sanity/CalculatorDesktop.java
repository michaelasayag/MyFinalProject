package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.APIFlow;
import workflows.DesktopFlows;

@Listeners({utilities.Listeners.class})
public class CalculatorDesktop extends CommonOps {

    @Test(description = "Test 01: Verify Addition Command")
    @Description("This Test Verify Addition Command")
    public void test01_verifyAddition(){
        DesktopFlows.calculateAddition();
        Verifications.verifyTextInElement(calcMain.calculator_results,"Display is 9");
    }

}
