package com.ats.viewmodels;

import com.ats.models.ITask;
import java.util.List;

/**
 * Task List View Model
 * @author Alice Roherty-Carrier
 * @date 03-08-2020
 */
public class TaskListViewModel {
    private List<ITask> tasks;

    public List<ITask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks;
    }
}
