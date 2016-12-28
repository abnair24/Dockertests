package com.dockerSel.utils;


import com.relevantcodes.extentreports.ExtentReports;

/**
 * Created by aswathyn on 27/12/16.
 */
public class ExtentManager {

    static ExtentReports extent;
    final static String filePath = "target/Extent.html";

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }

        return extent;
    }
}
