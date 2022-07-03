package workflows;

import extensions.MobileActions;
import io.qameta.allure.Step;
import utilities.CommonOps;

public class MobileFlow extends CommonOps {

    @Step("Business Flow: Fill Form and Calculate Mortgage")
    public static void mortgageCalc(String amount, String term, String rate) {
        MobileActions.updateText(mortgageMain.txt_amount,amount);
        MobileActions.updateText(mortgageMain.txt_term,term);
        MobileActions.updateText(mortgageMain.txt_rate,rate);
        MobileActions.tap2(mortgageMain.btn_calculate);

    }
}
