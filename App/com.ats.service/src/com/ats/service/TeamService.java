/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.service;

import com.ats.models.ITeam;
import com.ats.repo.ITeamRepo;
import com.ats.repo.RepoFactory;
import java.util.List;

/**
 *
 * @author soake
 */
public class TeamService implements ITeamService {

    private ITeamRepo repo = RepoFactory.createTeamInstance();

    @Override
    public int insertTeam(ITeam team) {
        return repo.insertTeam(team);
    }

    @Override
    public int updateTeam(ITeam team) {
        return repo.updateTeam(team);
    }

    @Override
    public int deleteTeam(int id) {
        return repo.deleteTeam(id);
    }

    @Override
    public boolean isValid(ITeam team) {
        return team.getErrors().size() > 0;
    }
}
