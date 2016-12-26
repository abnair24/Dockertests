package com.dockerSel.tests;

import com.dockerSel.pages.*;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.testng.annotations.Test;

/**
 * Created by aswathyn on 26/12/16.
 */
public class SampleTest1 extends BaseTest {
    SignInPage signInPage ;
    DashBoardPage dashBoardPage;

    ClientHomePage clientHomePage;


    @Test
    public void updateClientDetails() throws Exception {
        System.out.println("Updating client details");
        signInPage = new SignInPage(driver);
        dashBoardPage =signInPage.signInAction("test1234@mailinator","test1234");
        clientHomePage = dashBoardPage.navToClientPage().searchClient("test company").selectClient().editClientDetail().editClientContact("test");
        clientHomePage.logout();
    }
}
