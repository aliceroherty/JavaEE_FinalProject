package com.ats.repo;

import com.ats.models.IJob;
import com.ats.models.ITask;
import java.util.List;

/**
 * Job Repository Interface
 * @author Alice Roherty-Carrier
 * @date 03-12-2020
 */
public interface IJobRepo {
    int insertJob(IJob job);
    int updateJob(IJob job);
    int deleteJob(int id);
    List<IJob> getJobs();
    List<ITask> getTasks(int jobID);
    List<IJob> getTeamJobs(int teamID);
}
