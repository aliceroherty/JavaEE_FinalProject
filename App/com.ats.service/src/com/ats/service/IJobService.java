package com.ats.service;

import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.ITeam;
import java.util.Date;
import java.util.List;

/**
 * Job Service Interface
 * @author Alice Roherty-Carrier
 * @date 12-03-2020
 */
public interface IJobService {
    int insertJob(IJob job);
    int updateJob(IJob job);
    int deleteJob(int id);
    List<IJob> getJobs();
    void addError(IJob job, String message);
    boolean isValid(IJob job);
    double calculateCost(IJob job);
    double calculateRevenue(IJob job);
    boolean isDuringBusinessHours(IJob job);
    boolean teamMeetsRequirements(IJob job);
    boolean isInPast(Date date);
    boolean teamIsBooked(IJob job);
    List<ITask> getTasks(int jobID);
}
