package com.ats.repo;

import com.ats.dataaccess.DALFactory;
import com.ats.dataaccess.IDAL;
import com.ats.dataaccess.IParameter;
import com.ats.dataaccess.IParameter.Direction;
import com.ats.dataaccess.ParameterFactory;
import com.ats.models.ITask;
import com.ats.models.TaskFactory;
import java.sql.Types;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 * Task Repository Class
 *
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class TaskRepo extends BaseRepo implements ITaskRepo {

    private IDAL db = DALFactory.createInstance();

    @Override
    public int insertTask(ITask task) {
        int id = 0;
        List<Object> returnParams;
        List<IParameter> params = ParameterFactory.createListInstance();

        IParameter idParam = ParameterFactory.createInstance(id, IParameter.Direction.OUT, Types.INTEGER);
        params.add(idParam);

        IParameter name = ParameterFactory.createInstance(task.getName(), IParameter.Direction.IN, Types.VARCHAR);
        params.add(name);

        IParameter description = ParameterFactory.createInstance(task.getDescription(), IParameter.Direction.IN, Types.VARCHAR);
        params.add(description);

        IParameter duration = ParameterFactory.createInstance(task.getDuration(), IParameter.Direction.IN, Types.INTEGER);
        params.add(duration);

        returnParams = db.executeNonQuery("CALL Task_Insert(?, ?, ?, ?)", params);

        try {
            if (returnParams != null && returnParams.size() != 0) {
                id = Integer.parseInt(returnParams.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public int updateTask(ITask task) {
        return 0;
    }

    @Override
    public int deleteTask(int id) {
        int rowsAffected = 0;

        List<Object> returnValues;
        List<IParameter> params = ParameterFactory.createListInstance();

        params.add(ParameterFactory.createInstance(id));

        returnValues = db.executeNonQuery("CALL Task_Delete(?)", params);

        try {
            rowsAffected = Integer.parseInt(returnValues.get(0).toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return rowsAffected;
    }

    /**
     * Gets all tasks from database.
     *
     * @return List of tasks retrieved.
     */
    @Override
    public List<ITask> getTasks() {
        List<ITask> tasks = TaskFactory.createListInstance();

        CachedRowSet results = db.executeFill("CALL Tasks_GetTasks();", ParameterFactory.createListInstance());

        try {
            while (results.next()) {
                ITask task = TaskFactory.createInstance();

                task.setId(getInt("ID", results));
                task.setName(getString("Name", results));
                task.setDescription(getString("Description", results));
                task.setDuration(getInt("Duration", results));

                tasks.add(task);
            }
        } catch (Exception ex) {
        }

        return tasks;
    }

    /**
     * Gets a task with a specified ID.
     *
     * @param id The ID that specifies which task should be retrieved.
     * @return The task retrieved.
     */
    @Override
    public ITask getTask(int id) {
        ITask task = TaskFactory.createInstance();

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(id, Direction.IN, Types.INTEGER));

        CachedRowSet results = db.executeFill("CALL Tasks_GetTask(?);", params);

        try {
            if (results.next()) {
                task = TaskFactory.createInstance(
                        getInt("ID", results),
                        getString("Name", results),
                        getString("Description", results),
                        getInt("Duration", results)
                );
            }
        } catch (Exception ex) {
        }

        return task;
    }

}
