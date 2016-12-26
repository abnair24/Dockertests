package com.dockerSel.tests;

import com.dockerSel.pages.ClientHomePage;
import com.dockerSel.pages.DashBoardPage;
import com.dockerSel.pages.EditClientPage;
import com.dockerSel.pages.SignInPage;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.testng.annotations.Test;

/**
 * Created by aswathyn on 26/12/16.
 */
public class SampleTest2 extends BaseTest {

    SignInPage signInPage ;
    DashBoardPage dashBoardPage;
    EditClientPage editClientPage;

    @Test
    public void verifyEmail() throws Exception {
        System.out.println("Verifying client details");
        signInPage = new SignInPage(driver);
        dashBoardPage =signInPage.signInAction("test1234@mailinator","test1234");
        editClientPage= dashBoardPage.navToClientPage().searchClient("test company").selectClient().editClientDetail().verifyClientEmail("test1234@mailinator.com");
        editClientPage.logOut();
    }
}
