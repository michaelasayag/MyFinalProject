package sanity;

import extensions.MobileActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.MobileFlow;



@Listeners({utilities.Listeners.class})
public class MortgageMobile extends CommonOps {
    @Test(description = "Test01 - Verify Mortgage")
    @Description("This Test Fill in mortgage fields and calculate repayment")
    public void test01_verifyMortgage() throws Exception {
        MobileFlow.mortgageCalc("1000", "3", "4");
        Verifications.verifyTextInElement(mortgageMain.txt_repayment, "£30.03");
    }


}
