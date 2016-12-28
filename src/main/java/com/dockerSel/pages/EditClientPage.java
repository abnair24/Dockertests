package com.dockerSel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * Created by aswathyn on 26/12/16.
 */
public class EditClientPage extends BasePage<EditClientPage> {

    @FindBy(xpath = ".//h3[text()='Editing Client']")
    private WebElement editHeader;

    @FindBy(id="client_contact_person_name")
    private WebElement contactPerson;

    @FindBy(xpath=".//input[@name='commit']")
    private WebElement updateButton;

    @FindBy(id="client_email")
    private WebElement clientEmail;

    @FindBy(xpath = ".//span[@class='glyphicon glyphicon-log-out']")
    private WebElement logOut;

    public EditClientPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(EditClientPage page) {
        PageFactory.initElements(driver,page);
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(editHeader);
    }

    public ClientHomePage editClientContact(String clientName) throws Exception {
        enterText(contactPerson,timeStamp(clientName));
        clickButton(updateButton);
        return new ClientHomePage(driver);
    }

    public EditClientPage verifyClientEmail(String email) throws Exception {

        Assert.assertEquals(clientEmail.getAttribute("value"),email);
        return this;
    }

    public SignInPage logOut() throws Exception {
        clickIcon(logOut,"Logging out");
        return new SignInPage(driver);
    }
}
