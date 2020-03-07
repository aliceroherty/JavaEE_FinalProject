package com.ats.dataaccess;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Data Access Helper Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class DALHelper {
    public static Properties getProperties() throws Exception {
        Properties props = new Properties();
        InputStream in = null;

        try {
            ClassLoader classLoader = DALHelper.class.getClassLoader();
            in = classLoader.getResourceAsStream("com/ats/properties/db.properties");
           
            if (in != null) {
                props.load(in);
                in.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return props;
    }
}
