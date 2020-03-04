package com.ats.service;

import com.ats.models.ITask;
import java.util.List;

/**
 * Task Service Interface
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public interface ITaskService {
    int insertTask(ITask task);
    int updateTask(ITask task);
    int deleteTask(int id);
    List<ITask> getTasks();
    ITask getTask(int id);
}
