package com.ats.repo;

import com.ats.models.IEmployee;
import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.ITeam;
import java.util.List;

/**
 * Team Repository Interface
 * @author Sam Oakes
 * @date 04-03-2020
 */
public interface ITeamRepo {
    int insertTeam(ITeam team);
    int updateTeam(ITeam team);
    int deleteTeam(int id);
    List<ITeam> getTeams();
    List<IEmployee> getTeamMembers(int teamID);
    ITeam getTeam(int id);
    List<IJob> getJobs(int id);
    List<ITask> getTasks(int jobID);
    boolean doesExist_TeamOnCall();
}
