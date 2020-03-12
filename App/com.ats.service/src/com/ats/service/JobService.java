package com.ats.service;

import com.ats.models.IJob;
import com.ats.repo.IJobRepo;
import com.ats.repo.RepoFactory;
import java.util.List;

/**
 * Job Service Class
 * @author Alice Roherty-Carrier
 * @date 12-03-2020
 */
public class JobService implements IJobService {
    IJobRepo repo = RepoFactory.createJobInstance();
    
    @Override
    public int insertJob(IJob job) {
        return repo.insertJob(job);
    }

    @Override
    public int updateJob(IJob job) {
        return repo.updateJob(job);
    }

    @Override
    public int deleteJob(int id) {
        return repo.deleteJob(id);
    }

    @Override
    public List<IJob> getJobs() {
        return repo.getJobs();
    }
}
