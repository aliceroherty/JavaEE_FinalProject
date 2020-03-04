package com.ats.models;

import java.util.ArrayList;

/**
 * Error Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class ErrorFactory {
    public static IError createInstance() {
        return new Error();
    }
    
    public static IError createInstance(int code, String description) {
        return new Error(code, description);
    }
    
    public static ArrayList<IError> createListInstance() {
        return new ArrayList<>();
    }
}
