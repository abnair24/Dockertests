package com.dockerSel.tests;

import com.dockerSel.utils.ExtentManager;
import com.dockerSel.utils.ExtentTestManager;
import com.dockerSel.utils.PropertyReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by aswathyn on 26/12/16.
 */
public class BaseTest {

    public WebDriver driver;
    protected DesiredCapabilities desiredCapabilities;
    ExtentTest extentTest;

    @BeforeClass
    @Parameters({"browserType"})
    public void setup(String browser) throws Exception {

        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",PropertyReader.getPropValue("chromepath"));
            desiredCapabilities = DesiredCapabilities.chrome();
            driver = new ChromeDriver(desiredCapabilities);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            int Width = (int) toolkit.getScreenSize().getWidth();
            int Height = (int) toolkit.getScreenSize().getHeight();
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(Width, Height));
        }
        else if(browser.equals("firefox")) {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setEnableNativeEvents(true);

            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
            driver = new FirefoxDriver(desiredCapabilities);
        }


        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);
        System.out.println(PropertyReader.getPropValue("url"));
        driver.get(PropertyReader.getPropValue("url"));

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        extentTest=ExtentTestManager.startTest(method.getName());
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try{
                ExtentTestManager.getTest().log(LogStatus.FAIL, result.getMethod().getMethodName(),
                        extentTest.addScreenCapture(CaptureScreenShot(driver,result.getMethod().getMethodName())));

            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getMethod().getMethodName());
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed",result.getMethod().getMethodName());
        }

        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterClass
    public void teardown() throws Exception {
        driver.quit();
    }

    public String CaptureScreenShot(WebDriver driver, String screenShotName) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        String screenshot= System.getProperty("user.dir")+"/target/"+PropertyReader.timeStamp(screenShotName)+".png";

        FileUtils.copyFile(scrFile, new File(screenshot));
        return screenshot;
    }


}
