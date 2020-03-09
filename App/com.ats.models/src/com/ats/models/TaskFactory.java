package com.ats.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Task Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class TaskFactory {
    public static ITask createInstance() {
        return new Task();
    }
    
    public static ITask createInstance(int id, String name, String description, int duration) {
        return new Task(name, description, duration);
    }
    
    public static ITask createInstance(String name, String description, int duration) {
        return new Task(name, description, duration);
    }
    
    public static List<ITask> createListInstance() {
        return new ArrayList<>();
    }
}
