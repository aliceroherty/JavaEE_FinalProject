package com.ats.service;

/**
 * Service Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public abstract class ServiceFactory {
    public static IEmployeeService createEmployeeInstance() {
        return new EmployeeService();
    }
    
    public static ITeamService createTeamInstance(){
        return new TeamService();
    }
    
    public static ITaskService createTaskInstance() {
        return new TaskService();
    }
    
    public static IJobService createJobInstance() {
        return new JobService();
    }
}
