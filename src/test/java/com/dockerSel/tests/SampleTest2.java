package com.dockerSel.tests;

import com.dockerSel.pages.ClientHomePage;
import com.dockerSel.pages.DashBoardPage;
import com.dockerSel.pages.EditClientPage;
import com.dockerSel.pages.SignInPage;
import com.dockerSel.utils.PropertyReader;
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
        dashBoardPage =signInPage.signInAction(PropertyReader.getPropValue("username"),PropertyReader.getPropValue("password"));
        editClientPage= dashBoardPage.navToClientPage().searchClient(PropertyReader.getPropValue("searchClient"))
                .selectClient().editClientDetail().verifyClientEmail(PropertyReader.getPropValue("clientEmail"));
        editClientPage.logOut();
    }
}
