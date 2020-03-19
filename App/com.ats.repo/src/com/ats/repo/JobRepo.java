package com.ats.repo;

import com.ats.dataaccess.DALFactory;
import com.ats.dataaccess.IDAL;
import com.ats.dataaccess.IParameter;
import com.ats.dataaccess.ParameterFactory;
import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.JobFactory;
import com.ats.models.TaskFactory;
import java.sql.Types;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 * Job Repository Class
 *
 * @author Alice Roherty-Carrier
 * @date 03-12-2020
 */
public class JobRepo extends BaseRepo implements IJobRepo {

    private IDAL db = DALFactory.createInstance();
    private ITeamRepo teamRepo = RepoFactory.createTeamInstance();

    @Override
    public int insertJob(IJob job) {
        int id = 0;
        List<Object> returnValues;
        List<IParameter> params = ParameterFactory.createListInstance();

        params.add(ParameterFactory.createInstance(job.getId(), IParameter.Direction.OUT, Types.INTEGER));
        params.add(ParameterFactory.createInstance(job.getDescription()));
        params.add(ParameterFactory.createInstance(job.getClientName()));
        params.add(ParameterFactory.createInstance(job.getCost()));
        params.add(ParameterFactory.createInstance(job.getRevenue()));
        params.add(ParameterFactory.createInstance(job.getStartTime()));
        params.add(ParameterFactory.createInstance(job.getEndTime()));
        params.add(ParameterFactory.createInstance(job.getTeam().getId()));

        returnValues = db.executeNonQuery("CALL Jobs_Insert(?, ?, ?, ?, ?, ?, ?, ?);", params);

        try {
            if (returnValues != null && !returnValues.isEmpty()) {
                id = Integer.parseInt(returnValues.get(0).toString());

                for (ITask task : job.getTasks()) {
                    List<IParameter> jobTaskParams = ParameterFactory.createListInstance();
                    int taskId = task.getId();
                    jobTaskParams.add(ParameterFactory.createInstance(taskId));
                    jobTaskParams.add(ParameterFactory.createInstance(id));

                    db.executeNonQuery("CALL JobTasks_Insert(?, ?);", jobTaskParams);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public int updateJob(IJob job) {
        return 0;
    }

    @Override
    public int deleteJob(int id) {
        int rowsAffected = 0;

        List<Object> returnValues;

        List<IParameter> params = ParameterFactory.createListInstance();

        params.add(ParameterFactory.createInstance(id));

        returnValues = db.executeNonQuery("CALL Job_Delete(?)", params);
        
        try {
            rowsAffected = Integer.parseInt(returnValues.get(0).toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return rowsAffected;
    }

    @Override
    public List<IJob> getJobs() {
        List<IJob> jobs = JobFactory.createListInstance();

        CachedRowSet results = db.executeFill("CALL Jobs_GetAll();", ParameterFactory.createListInstance());

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
                        teamRepo.getTeam(getInt("TeamID", results))
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

    @Override
    public List<IJob> getTeamJobs(int teamID) {
        List<IJob> jobs = JobFactory.createListInstance();

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(teamID));

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
                        teamRepo.getTeam(teamID)
                );

                jobs.add(job);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return jobs;
    }

}
