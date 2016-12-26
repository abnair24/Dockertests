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
public class SignInPage extends BasePage<SignInPage> {

    @FindBy(xpath=".//h3[text()='Sign in']")
    private WebElement signInHeader;

    @FindBy(id="user_email")
    private WebElement email;

    @FindBy(id="user_password")
    private WebElement pwd;

    @FindBy(xpath=".//input[@name='commit']")
    private WebElement signInButton;


    public SignInPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SignInPage.class);

    @Override
    protected void instantiatePage(SignInPage page) {
        try {
            PageFactory.initElements(driver,page);
        } catch(Exception ex) {
            LOGGER.error("Instantiation failed");
        }
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(signInHeader);
    }

    public DashBoardPage signInAction(String emailId, String password) throws Exception {
        enterText(email,emailId);
        enterText(pwd,password);
        clickButton(signInButton);
        return new DashBoardPage(driver);
    }
}
