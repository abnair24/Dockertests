package com.dockerSel.tests;

import com.dockerSel.pages.*;
import com.dockerSel.utils.ExtentTestManager;
import com.dockerSel.utils.PropertyReader;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.internal.ws.wsdl.writer.document.Port;
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
        ExtentTestManager.getTest().log(LogStatus.PASS, "Log from threadId: " + Thread.currentThread().getId());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Log from threadId: " + Thread.currentThread().getId());
        System.out.println("Updating client details");
        signInPage = new SignInPage(driver);
        System.out.println(PropertyReader.getPropValue("username"));
        dashBoardPage =signInPage.signInAction(PropertyReader.getPropValue("username"),PropertyReader.getPropValue("password"));
        clientHomePage = dashBoardPage.navToClientPage().searchClient(PropertyReader.getPropValue("searchClient"))
                .selectClient().editClientDetail().editClientContact("test");
        clientHomePage.logout();
    }
}
