package com.ats.service;

import com.ats.models.IJob;
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
}
