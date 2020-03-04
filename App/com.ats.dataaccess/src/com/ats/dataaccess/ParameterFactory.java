package com.ats.dataaccess;

import com.ats.dataaccess.IParameter.Direction;
import java.util.ArrayList;

/**
 * Parameter Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class ParameterFactory {
    public static IParameter createInstance() {
        return new Parameter();
    }
    
    public static IParameter createInstance(Object value) {
        return new Parameter(value);
    }
    
    public static IParameter createInstance(Object value, Direction direction) {
        return new Parameter(value, direction);
    }
    
    public static IParameter createInstance(Object value, Direction direction, int sqlType) {
        return new Parameter(value, direction, sqlType);
    }
    
    public static ArrayList<IParameter> createListInstance() {
        return new ArrayList<>();
    }
}
