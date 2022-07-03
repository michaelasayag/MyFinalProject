package sanity;

import com.sun.org.glassfish.gmbal.DescriptorFields;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.APIFlow;


@Listeners({utilities.Listeners.class})
public class GrafanaAPI extends CommonOps {


    @Test(description = "Test 01: Get Team From Grafana")
    @Description("This Test Verify Team Property")
    public void test01_verifyTeam(){
        Verifications.verifyText(APIFlow.getTeamProperty("teams[0].name"),"A-Team");
    }

    @Test(description = "Test 02: Add New Team And Verify")
    @Description("This Test Add New Team And Verify it")
    public void test02_AddTeamAndVerify(){
        APIFlow.postTeam("IMF","ethanhunt@imf.gov");
        Verifications.verifyText(APIFlow.getTeamProperty("teams[1].name"),"IMF");
    }

    @Test(description = "Test 03: Update Team And Verify")
    @Description("This Test Update a Team And Verify it")
    public void test03_updateTeamAndVerify(){
        String id = APIFlow.getTeamProperty("teams[1].id");
        APIFlow.updateTeam("IMF","ethanhunt@imf.gov",id);
        Verifications.verifyText(APIFlow.getTeamProperty("teams[1].email"),"ethanhunt@imf.gov");
    }

    @Test(description = "Test 04: Delete Team And Verify")
    @Description("This Test Delete a Team And Verify it")
    public void test04_deleteTeamAndVerify(){
        String id = APIFlow.getTeamProperty("teams[1].id");
        APIFlow.deleteTeam(id);
        Verifications.verifyText(APIFlow.getTeamProperty("totalCount"),"1");
    }

}
