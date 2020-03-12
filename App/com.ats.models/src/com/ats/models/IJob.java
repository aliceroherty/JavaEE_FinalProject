package com.ats.models;

import java.util.Date;
import java.util.List;

/**
 * Job Interface
 * @author Alice Roherty-Carrier
 * @date 03-12-2020
 */
public interface IJob extends IBase {
    public int getId();

    public void setId(int id);

    public String getDescription();

    public void setDescription(String description);

    public String getClientName();

    public void setClientName(String clientName);

    public double getCost();

    public void setCost(double cost);

    public double getRevenue();

    public void setRevenue(double revenue);

    public Date getStartTime();

    public void setStartTime(Date startTime);

    public Date getEndTime();

    public void setEndTime(Date endTime);
    
    public List<ITask> getTasks();

    public void setTasks(List<ITask> tasks);
    
    public ITeam getTeam();

    public void setTeam(ITeam team);
}
