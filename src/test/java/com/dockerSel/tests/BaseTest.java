package com.dockerSel.tests;

import com.dockerSel.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import sun.security.krb5.internal.crypto.Des;

import java.net.URL;

/**
 * Created by aswathyn on 26/12/16.
 */
public class BaseTest {

    public WebDriver driver;
    protected DesiredCapabilities desiredCapabilities;
    PropertyReader propertyReader;

    @BeforeClass
    @Parameters({"browserType"})
    public void setup(String browser) throws Exception {
        if(browser.equals("chrome")) {
            desiredCapabilities = DesiredCapabilities.chrome();
        } else if(browser.equals("firefox")) {
            desiredCapabilities = DesiredCapabilities.firefox();
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);
        driver.get(PropertyReader.getPropValue("url"));

    }

    @AfterClass
    public void teardown() throws Exception {
        driver.quit();
    }




}
