package sanity;

import extensions.DBActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.WebFlows;


@Listeners({utilities.Listeners.class})
public class GrafanaWebDB extends CommonOps {
        @Test(description = "Test01 - Login To Grafana With DB Credentials")
        @Description("This Test Login with DB Credentials and Verified the main header")
        public void test01_loginWithDbAndVerifyheader(){
            WebFlows.loginDB();
            Verifications.verifyTextInElement(grafanaMain.head_dashboard,"Welcome to Grafana");
        }
}
