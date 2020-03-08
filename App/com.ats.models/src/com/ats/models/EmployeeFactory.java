package com.ats.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Employee Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class EmployeeFactory {
    public static IEmployee createInstance() {
        return new Employee();
    }
    
    public static IEmployee createInstance(String firstName, String lastName, int sin, double hourlyRate, Date createdAt, boolean isDeleted) {
        return new Employee(firstName, lastName, sin, hourlyRate, createdAt, isDeleted);
    }
    
    public static List<IEmployee> createListInstance() {
        return new ArrayList<>();
    }
}
