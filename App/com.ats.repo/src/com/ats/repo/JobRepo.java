package com.ats.repo;

import com.ats.dataaccess.DALFactory;
import com.ats.dataaccess.IDAL;
import com.ats.dataaccess.IParameter;
import com.ats.dataaccess.ParameterFactory;
import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.JobFactory;
import java.sql.Types;
import java.util.List;

/**
 * Job Repository Class
 *
 * @author Alice Roherty-Carrier
 * @date 03-12-2020
 */
public class JobRepo extends BaseRepo implements IJobRepo {

    private IDAL db = DALFactory.createInstance();

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

                    jobTaskParams.add(ParameterFactory.createInstance(task.getId()));
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
        return 0;
    }

    @Override
    public List<IJob> getJobs() {
        return JobFactory.createListInstance();
    }

}
