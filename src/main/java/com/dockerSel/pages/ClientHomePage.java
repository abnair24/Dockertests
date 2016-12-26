package com.dockerSel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 26/12/16.
 */
public class ClientHomePage extends BasePage<ClientHomePage> {

    @FindBy(xpath=".//a[@class='edit']")
    private WebElement editClient;

    @FindBy(xpath = ".//span[@class='glyphicon glyphicon-log-out']")
    private WebElement logOut;

    public ClientHomePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(ClientHomePage page) {
        PageFactory.initElements(driver,page);
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(editClient);
    }

    public EditClientPage editClientDetail() throws Exception {
        clickButton(editClient);
        return new EditClientPage(driver);
    }

    public SignInPage logout() throws Exception {
        clickIcon(logOut,"Loggin out");
        return new SignInPage(driver);
    }
}
