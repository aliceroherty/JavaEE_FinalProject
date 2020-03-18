package com.ats.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Job Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-12-2020
 */
public abstract class JobFactory {
    public static IJob createInstance() {
        return new Job();
    }
    
    public static IJob createInstance(int id, String description, String clientName, double cost, double revenue, Date startTime, Date endTime, List<ITask> tasks, ITeam team) {
        return new Job(id, description, clientName, cost, revenue, startTime, endTime, tasks, team);
    }
    
    public static IJob createInstance(String description, String clientName, double cost, double revenue, Date startTime, Date endTime, List<ITask> tasks, ITeam team) {
        return new Job(description, clientName, cost, revenue, startTime, endTime, tasks, team);
    }
    
    public static List<IJob> createListInstance() {
        return new ArrayList<>();
    }
}
