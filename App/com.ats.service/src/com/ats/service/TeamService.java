/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.service;

import com.ats.models.IEmployee;
import com.ats.models.ITeam;
import com.ats.repo.ITeamRepo;
import com.ats.repo.RepoFactory;
import java.util.Date;
import java.util.List;

/**
 *
 * @author soake
 */
public class TeamService implements ITeamService {

    private ITeamRepo repo = RepoFactory.createTeamInstance();

    @Override
    public int insertTeam(ITeam team) {
        if (repo.doesExist_TeamOnCall()) {
            return 0;
        }
        return repo.insertTeam(team);
    }

    @Override
    public int updateTeam(ITeam team) {
        return repo.updateTeam(team);
    }

    @Override
    public int deleteTeam(int id) {
        if (repo.getJobs(id).isEmpty()) {
            return repo.deleteTeam(id);
        } else {
            ITeam team = getTeam(id);
            team.setDeletedAt(new Date());
            team.setIsDeleted(true);
            return repo.updateTeam(team);
        }
    }

    @Override
    public boolean isValid(ITeam team) {
        return team.getErrors().size() > 0;
    }

    @Override
    public List<ITeam> getTeams() {
        return repo.getTeams();
    }

    @Override
    public List<IEmployee> getTeamMembers(int teamID) {
        return repo.getTeamMembers(teamID);
    }

    @Override
    public ITeam getTeam(int id) {
        return repo.getTeam(id);
    }
}
