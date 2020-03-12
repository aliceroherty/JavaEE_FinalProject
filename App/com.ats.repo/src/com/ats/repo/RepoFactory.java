package com.ats.repo;

/**
 * Repository Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public abstract class RepoFactory {
    /**
     * Creates an instance of an Employee Repository.
     * @return Employee Repository Instance.
     */
    public static IEmployeeRepo createEmployeeInstance() {
        return new EmployeeRepo();
    }
    
    /**
     * Creates an instance of a Task Repository.
     * @return Task Repository Instance.
     */
    public static ITaskRepo createTaskInstance() {
        return new TaskRepo();
    }
    
     /**
     * Creates an instance of a Team Repository.
     * @return Team Repository Instance.
     */
    public static ITeamRepo createTeamInstance() {
        return new TeamRepo();
    }
    
    /**
     * Creates an instance of a Job Repository.
     * @return Job Repository Instance
     */
    public static IJobRepo createJobInstance() {
        return new JobRepo();
    }
}
