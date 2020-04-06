/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.repo;

import com.ats.dataaccess.DALFactory;
import com.ats.dataaccess.IDAL;
import com.ats.dataaccess.IParameter;
import com.ats.dataaccess.ParameterFactory;
import com.ats.models.EmployeeFactory;
import com.ats.models.IEmployee;
import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.ITeam;
import com.ats.models.JobFactory;
import com.ats.models.TaskFactory;
import com.ats.models.TeamFactory;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.List;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;

/**
 * Team Repository Class
 *
 * @author Samuel Oakes
 * @date 03-04-2020
 */
public class TeamRepo extends BaseRepo implements ITeamRepo {

    private IDAL db = DALFactory.createInstance();
    private IEmployeeRepo employeeRepo = RepoFactory.createEmployeeInstance();

    /**
     * Creates a team.
     *
     * @param team The Team to be created.
     * @return The number of rows affected.
     */
    @Override
    public boolean doesExist_TeamOnCall() {

        CachedRowSet results = db.executeFill("CALL Team_IsOnCall();", null);

        return results.size() != 0;

    }

    @Override
    public int insertTeam(ITeam team) {
        int id = 0;
        List<Object> returnParams;
        List<IParameter> params = ParameterFactory.createListInstance();

        IParameter idParam = ParameterFactory.createInstance(id, IParameter.Direction.OUT, Types.INTEGER);
        params.add(idParam);

        IParameter name = ParameterFactory.createInstance(team.getName(), IParameter.Direction.IN, Types.VARCHAR);
        params.add(name);

        IParameter onCall = ParameterFactory.createInstance(team.isOnCall(), IParameter.Direction.IN, Types.BOOLEAN);
        params.add(onCall);

        IParameter createdAt = ParameterFactory.createInstance(team.getCreatedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(createdAt);

        IParameter updatedAt = ParameterFactory.createInstance(team.getUpdatedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(updatedAt);

        IParameter isDeleted = ParameterFactory.createInstance(team.isDeleted(), IParameter.Direction.IN, Types.BOOLEAN);
        params.add(isDeleted);

        IParameter deletedAt = ParameterFactory.createInstance(team.getDeletedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(deletedAt);

        returnParams = db.executeNonQuery("CALL Team_Insert(?,?,?,?,?,?,?);", params);

        try {
            if (returnParams != null && !returnParams.isEmpty()) {

                id = Integer.parseInt(returnParams.get(0).toString());

                for (IEmployee employee : team.getEmployees()) {
                    List<IParameter> teamMemberParams = ParameterFactory.createListInstance();

                    teamMemberParams.add(ParameterFactory.createInstance(employee.getId()));
                    teamMemberParams.add(ParameterFactory.createInstance(id));

                    db.executeNonQuery("CALL TeamMember_Insert(?, ?);", teamMemberParams);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public int updateTeam(ITeam team) {
        int rowsAffected = 0;
        List<Object> returnValues;
        List<IParameter> params = ParameterFactory.createListInstance();

        params.add(ParameterFactory.createInstance(team.getId()));
        params.add(ParameterFactory.createInstance(team.getName()));
        params.add(ParameterFactory.createInstance(team.isOnCall()));
        params.add(ParameterFactory.createInstance(team.getCreatedAt()));
        params.add(ParameterFactory.createInstance(team.getUpdatedAt()));
        params.add(ParameterFactory.createInstance(team.isDeleted()));
        params.add(ParameterFactory.createInstance(team.getDeletedAt()));

        returnValues = db.executeNonQuery("CALL Team_Update(?, ?, ?, ?, ?, ?, ?);", params);

        try {
            if (returnValues != null) {
                rowsAffected = Integer.parseInt(returnValues.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rowsAffected;
    }

    @Override
    public int deleteTeam(int id) {
        int rowsAffected = 0;

        List<Object> returnValues;

        List<IParameter> params = ParameterFactory.createListInstance();

        params.add(ParameterFactory.createInstance(id));

        returnValues = db.executeNonQuery("CALL Team_Delete(?);", params);

        try {
            rowsAffected = Integer.parseInt(returnValues.get(0).toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return rowsAffected;
    }

    @Override
    public List<ITeam> getTeams() {
        List<ITeam> teams = TeamFactory.createListInstance();

        CachedRowSet results = db.executeFill("CALL Team_GetAll();", ParameterFactory.createListInstance());

        try {
            while (results.next()) {
                ResultSetMetaData data = results.getMetaData();

                ITeam team = TeamFactory.createInstance(
                        getInt("ID", results),
                        getString("Name", results),
                        getBoolean("IsOnCall", results),
                        getDate("CreatedAt", results),
                        getDate("UpdatedAt", results),
                        getBoolean("IsDeleted", results),
                        getDate("DeletedAt", results),
                        getTeamMembers(getInt("ID", results))
                );

                teams.add(team);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return teams;
    }

    @Override
    public List<IEmployee> getTeamMembers(int teamID) {
        List<IEmployee> employees = EmployeeFactory.createListInstance();

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(teamID));

        CachedRowSet results = db.executeFill("CALL Team_GetTeamMembers(?);", params);

        try {
            while (results.next()) {
                IEmployee employee = EmployeeFactory.createInstance(
                        getInt("ID", results),
                        getString("FirstName", results),
                        getString("LastName", results),
                        getInt("SIN", results),
                        getDouble("HourlyRate", results),
                        getDate("CreatedAt", results),
                        getBoolean("isDeleted", results),
                        getDate("UpdatedAt", results),
                        getDate("DeletedAt", results),
                        employeeRepo.getSkills(getInt("ID", results))
                );

                employees.add(employee);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return employees;
    }

    @Override
    public ITeam getTeam(int id) {
        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(id));
        CachedRowSet results = db.executeFill("CALL Team_GetByID(?);", params);
        ITeam team = TeamFactory.createInstance();

        try {
            if (results.next()) {
                team.setId(getInt("ID", results));
                team.setName(getString("Name", results));
                team.setIsOnCall(getBoolean("IsOnCall", results));
                team.setCreatedAt(getDate("CreatedAt", results));
                team.setUpdatedAt(getDate("UpdatedAt", results));
                team.setIsDeleted(getBoolean("IsDeleted", results));
                team.setDeletedAt(getDate("DeletedAt", results));
                team.setEmployees(getTeamMembers(getInt("ID", results)));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return team;
    }

    @Override
    public List<IJob> getJobs(int id) {
        List<IJob> jobs = JobFactory.createListInstance();

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(id));

        CachedRowSet results = db.executeFill("CALL Team_GetJobs(?);", params);

        try {
            while (results.next()) {
                IJob job = JobFactory.createInstance(
                        getInt("ID", results),
                        getString("Description", results),
                        getString("ClientName", results),
                        getDouble("Cost", results),
                        getDouble("Revenue", results),
                        getDate("StartTime", results),
                        getDate("EndTime", results),
                        getTasks(getInt("ID", results)),
                        getTeam(id)
                );

                jobs.add(job);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return jobs;
    }

    @Override
    public List<ITask> getTasks(int jobID) {
        List<ITask> tasks = TaskFactory.createListInstance();

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(jobID));

        CachedRowSet results = db.executeFill("CALL Jobs_GetTasks(?);", params);

        try {
            while (results.next()) {
                ITask task = TaskFactory.createInstance(
                        getInt("ID", results),
                        getString("Name", results),
                        getString("Description", results),
                        getInt("Duration", results)
                );

                tasks.add(task);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }
}
