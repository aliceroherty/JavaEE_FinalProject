package com.ats.models;

/**
 * Employee Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class EmployeeFactory {
    public static IEmployee createInstance() {
        return new Employee();
    }
}
