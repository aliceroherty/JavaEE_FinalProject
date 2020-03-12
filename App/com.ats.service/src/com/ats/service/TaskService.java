package com.ats.service;

import com.ats.models.ITask;
import com.ats.repo.ITaskRepo;
import com.ats.repo.RepoFactory;
import java.util.List;

/**
 * Task Service Class
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
        return repo.updateTask(task);
    }

    @Override
    public int deleteTask(int id) {
        return repo.deleteTask(id);
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
