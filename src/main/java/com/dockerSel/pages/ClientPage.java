package com.dockerSel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 26/12/16.
 */
public class ClientPage extends BasePage<ClientPage> {

    @FindBy(xpath = ".//h1[text()='Clients']")
    private WebElement clientHeader;

    @FindBy(id="search")
    private WebElement searchField;

    @FindBy(className="searchBtn")
    private WebElement searchButton;

    @FindBy(xpath = ".//a[@class='info']")
    private WebElement selectClient;


    private static Logger LOGGER = LoggerFactory.getLogger(ClientPage.class);


    public ClientPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(ClientPage page) {
        PageFactory.initElements(driver,page);
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(clientHeader);
    }

    public ClientPage searchClient(String searchText) throws Exception {
        enterText(searchField,searchText);
        clickButton(searchButton);
        return new ClientPage(driver);
    }

    public ClientHomePage selectClient() throws Exception {
        clickButton(selectClient);
        return new ClientHomePage(driver);
    }
}
