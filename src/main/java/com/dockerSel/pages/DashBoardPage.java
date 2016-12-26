package com.dockerSel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 26/12/16.
 */
public class DashBoardPage extends BasePage<DashBoardPage> {

    @FindBy(xpath=".//h1[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath=".//span[@class='glyphicon glyphicon-log-out']")
    private WebElement logout;

    @FindBy(xpath=".//a[text()='Clients']")
    private WebElement clientPage;

    private static Logger LOGGER = LoggerFactory.getLogger(DashBoardPage.class);

    public DashBoardPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(DashBoardPage page) {
        try {
            PageFactory.initElements(driver,page);
        } catch(Exception ex) {
            LOGGER.error("Error instantiating page");
        }
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(dashboardHeader);
    }

    public ClientPage navToClientPage() throws Exception {
        clickIcon(clientPage,"Client Page");
        return new ClientPage(driver);
    }


}
