package com.dockerSel.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by aswathyn on 26/12/16.
 */
public class PropertyReader {


    private static String result = "";
    private static String propertyPath=null;

    private static InputStream inputStream;

    static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);

    public static String getPropValue(String item) throws IOException {
        FileInputStream fs =null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            propertyPath = Classpath.filePathFor("config.properties");
            fs = new FileInputStream(new File(propertyPath));
            if (fs != null) {
                prop.load(fs);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(item);
        } catch (Exception e) {
            LOGGER.error("Error in reading property file", e);
        } finally {
            fs.close();
        }
        return result;
    }
}
