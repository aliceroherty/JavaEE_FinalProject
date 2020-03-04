package com.ats.repo;

import com.ats.models.ITask;
import java.util.List;

/**
 * Task Repository Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface ITaskRepo {
    int insertTask(ITask task);
    int updateTask(ITask task);
    int deleteTask(int id);
    List<ITask> getTasks();
    ITask getTask(int id);
}
