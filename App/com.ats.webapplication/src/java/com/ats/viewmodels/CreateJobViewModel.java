package com.ats.viewmodels;

import com.ats.models.ITask;
import com.ats.models.ITeam;
import java.util.List;

/**
 * Create Job View Model
 * @author Alice Roherty-Carrier
 * @date 12-03-2020
 */
public class CreateJobViewModel {
    private List<ITask> skills;
    private List<ITeam> teams;

    public List<ITask> getSkills() {
        return skills;
    }

    public void setSkills(List<ITask> skills) {
        this.skills = skills;
    }

    public List<ITeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }
}
