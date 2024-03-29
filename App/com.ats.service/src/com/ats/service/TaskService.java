package com.ats.service;

import com.ats.models.ErrorFactory;
import com.ats.models.ITask;
import com.ats.repo.ITaskRepo;
import com.ats.repo.RepoFactory;
import java.util.List;

/**
 * Task Service Class
 *
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public class TaskService implements ITaskService {

    private ITaskRepo repo = RepoFactory.createTaskInstance();

    @Override
    public int insertTask(ITask task) {
        return repo.insertTask(task);
    }

    @Override
    public int updateTask(ITask task) {
        
        if (task.getId() == 0) {
            task.addError(ErrorFactory.createInstance(0, "ID required for Save"));
            return 0;
        } else if(!isValid(task)) {
            task.addError(ErrorFactory.createInstance(11, "Invalid Task Save"));
            return 0;
        } else {
            return repo.updateTask(task);
        }       
    }

    @Override
    public int deleteTask(int id) {
        if (id != 0) {
            return repo.deleteTask(id);
        } else {
            return 0;
        }
    }

    @Override
    public int deleteEmpTask(int empId, int taskId) {

        return repo.deleteEmpTask(empId, taskId);
    }

    @Override
    public List<ITask> getEmployeeTasks(int id) {
        return repo.getEmployeeTasks(id);
    }

    @Override
    public List<ITask> getTasks() {
        return repo.getTasks();
    }

    @Override
    public ITask getTask(int id) {
        return repo.getTask(id);
    }

    @Override
    public boolean isValid(ITask task) {
        return task.getErrors().size() > 0;
    }

}
