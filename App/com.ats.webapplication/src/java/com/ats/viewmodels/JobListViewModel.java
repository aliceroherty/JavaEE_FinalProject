package com.ats.viewmodels;

import com.ats.models.IJob;
import com.ats.models.ITeam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Job List View Model
 * @author Alice Roherty-Carrier
 * @date 03-17-2020
 */
public class JobListViewModel {
    private List<IJob> jobs = new ArrayList<>();
    private List<ITeam> teams = new ArrayList<>();
    private final String[] TIMES = {
        "8:00", 
        "8:30", 
        "9:00", 
        "9:30", 
        "10:00", 
        "10:30", 
        "11:00", 
        "11:30", 
        "12:00", 
        "12:30", 
        "1:00",
        "1:30",
        "2:00",
        "2:30",
        "3:00",
        "3:30",
        "4:00",
        "4:30",
        "5:00"
    };

    public List<ITeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }

    public List<IJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<IJob> jobs) {
        this.jobs = jobs;
    }
    
    public String[] getTimes() {
        return TIMES;
    }
    
    public static String getTime(Date date) {
        String strDateFormat = "h:mm";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}
