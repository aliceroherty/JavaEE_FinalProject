package com.ats.viewmodels;

import com.ats.models.ITeam;
import com.ats.models.TeamFactory;
import java.util.List;

/**
 * Team List View Model
 * @author Alice Roherty-Carrier
 * @date 03-23-2020
 */
public class TeamListViewModel {
    private List<ITeam> teams = TeamFactory.createListInstance();
    
    public TeamListViewModel(List<ITeam> teams) {
        this.teams = teams;
    }

    public List<ITeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }
}
