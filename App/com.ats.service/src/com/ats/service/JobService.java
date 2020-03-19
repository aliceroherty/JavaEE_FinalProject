package com.ats.service;

import com.ats.models.ErrorFactory;
import com.ats.models.IEmployee;
import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.ITeam;
import com.ats.repo.IJobRepo;
import com.ats.repo.RepoFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Job Service Class
 *
 * @author Alice Roherty-Carrier
 * @date 12-03-2020
 */
public class JobService implements IJobService {

    IJobRepo repo = RepoFactory.createJobInstance();
    //ITeamService teamService = ServiceFactory.createTeamInstance();

    @Override
    public int insertJob(IJob job) {
        if (job.getClientName().equals("")) {
            addError(job, "Client Name is required.");
        }

        if (job.getDescription().equals("")) {
            addError(job, "Description is required.");
        }

        if (job.getStartTime() == null) {
            addError(job, "Start Time is required.");
        } else if (isInPast(job.getStartTime())) {
            addError(job, "Start Time cannot be in the past.");
        }

        if (job.getTasks().isEmpty()) {
            addError(job, "Skills are required.");
        }

        if (job.getTeam() == null) {
            addError(job, "Team is required.");
        } else if (!isDuringBusinessHours(job) && !job.getTeam().isOnCall()) {
            addError(job, "Bookings can only be created for the on call team outside of business hours.");
        } else if (isDuringBusinessHours(job) && !teamMeetsRequirements(job)) {
            addError(job, "Team doesn't have the required skills for the job.");
        }

        if (job.getStartTime() != null && job.getTeam() != null && teamIsBooked(job)) {
            addError(job, "Team is already booked for this time.");
        }

        return isValid(job) ? repo.insertJob(job) : 0;
    }

    @Override
    public int updateJob(IJob job) {
        return repo.updateJob(job);
    }

    @Override
    public int deleteJob(int id) {
        if (id != 0) {
            return repo.deleteJob(id);

        } else {
            return 0;
        }
    }

    @Override
    public List<IJob> getJobs() {
        return repo.getJobs();
    }

    @Override
    public List<IJob> getTeamJobs(int teamID) {
        return repo.getTeamJobs(teamID);
    }

    @Override
    public void addError(IJob job, String message) {
        job.addError(ErrorFactory.createInstance(message));
    }

    @Override
    public boolean isValid(IJob job) {
        return job.getErrors().isEmpty();
    }

    @Override
    public double calculateCost(IJob job) {
        double costPerHour = 0;
        int minutes = 0;

        for (IEmployee employee : job.getTeam().getEmployees()) {
            costPerHour += employee.getHourlyRate();
        }

        for (ITask task : job.getTasks()) {
            minutes += task.getDuration();
        }

        return costPerHour * (minutes / 60);
    }

    @Override
    public double calculateRevenue(IJob job) {
        return isDuringBusinessHours(job) ? calculateCost(job) * 3 : calculateCost(job) * 4;
    }

    @Override
    public boolean isDuringBusinessHours(IJob job) {
        Calendar c = Calendar.getInstance();
        Date start = job.getStartTime();
        Date end = job.getEndTime();

        c.setTime(start);
        int startHour = c.get(Calendar.HOUR_OF_DAY);

        c.setTime(end);
        int endHour = c.get(Calendar.HOUR_OF_DAY);

        return startHour < 17 && startHour >= 8 && endHour < 17 && endHour >= 8;
    }

    @Override
    public boolean teamMeetsRequirements(IJob job) {
        List<IEmployee> team = job.getTeam().getEmployees();
        boolean meetsRequirements = true;

        for (IEmployee employee : team) {
            List<ITask> skills = employee.getSkills();

            for (ITask skill : job.getTasks()) {
                if (skills.stream().filter(s -> s.getId() == skill.getId()).toArray().length == 0) {
                    meetsRequirements = false;
                }
            }
        }

        return meetsRequirements;
    }

    @Override
    public boolean isInPast(Date date) {
        Date now = new Date();
        return date.before(now);
    }

    @Override
    public boolean teamIsBooked(IJob job) {
        /*ITeam team = job.getTeam();
        List<IJob> teamJobs = teamService.getJobs(team.getId());
        boolean isBooked = false;
        
        for (IJob teamJob : teamJobs) {
            //Check if job is within time of teamJob
            if (job.getStartTime().after(teamJob.getStartTime()) && job.getStartTime().before(teamJob.getEndTime())) {
                //Starts during teamJob
                isBooked = true;
            } else if (job.getEndTime().before(teamJob.getEndTime()) && job.getEndTime().after(teamJob.getStartTime())) {
                //Ends during teamJob
                isBooked = true;
            } else if (job.getStartTime().equals(teamJob.getStartTime()) || job.getEndTime().equals(teamJob.getEndTime())) {
                //Starts or ends  same time
                isBooked = true;
            }
        }
        
        return isBooked;*/
        return false;
    }

    @Override
    public List<ITask> getTasks(int jobID) {
        return repo.getTasks(jobID);
    }

}
