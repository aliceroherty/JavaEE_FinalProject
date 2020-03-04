package com.ats.dataaccess;

/**
 * Parameter Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface IParameter {
    public enum Direction{
        IN,
        OUT
    }
    
    void setValue(Object value);
    void setDirection(Direction type);
    void setSQLType(int sqlType);
    
    Object getValue();
    Direction getDirection();
    int getSQLType();
}
