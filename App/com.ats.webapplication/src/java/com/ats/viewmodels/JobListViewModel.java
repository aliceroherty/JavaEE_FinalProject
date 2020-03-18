package com.ats.viewmodels;

import com.ats.models.IJob;
import java.util.ArrayList;
import java.util.List;

/**
 * Job List View Model
 * @author Alice Roherty-Carrier
 * @date 03-17-2020
 */
public class JobListViewModel {
    private List<IJob> jobs = new ArrayList<>();

    public List<IJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<IJob> jobs) {
        this.jobs = jobs;
    }
}
