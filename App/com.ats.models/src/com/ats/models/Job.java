package com.ats.models;

import java.util.Date;
import java.util.List;

/**
 * Job Class
 * @author Alice Roherty-Carrier
 * @date 03-12-2020
 */
public class Job extends Base implements IJob {
    private int id;
    private String description;
    private String clientName;
    private double cost;
    private double revenue;
    private Date startTime;
    private Date endTime;
    private List<ITask> tasks;
    private ITeam team;
    
    public Job() {}
    
    public Job(String description, String clientName, double cost, double revenue, Date startTime, Date endTime, List<ITask> tasks, ITeam team) {
        this.description = description;
        this.clientName = clientName;
        this.cost = cost;
        this.revenue = revenue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tasks = tasks;
        this.team = team;
    }
    
    public Job(int id, String description, String clientName, double cost, double revenue, Date startTime, Date endTime, List<ITask> tasks, ITeam team) {
        this.id = id;
        this.description = description;
        this.clientName = clientName;
        this.cost = cost;
        this.revenue = revenue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tasks = tasks;
        this.team = team;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getClientName() {
        return clientName;
    }

    @Override
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public double getRevenue() {
        return revenue;
    }

    @Override
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Override
    public List<ITask> getTasks() {
        return tasks;
    }

    @Override
    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks;
    }

    public ITeam getTeam() {
        return team;
    }

    public void setTeam(ITeam team) {
        this.team = team;
    }
}
