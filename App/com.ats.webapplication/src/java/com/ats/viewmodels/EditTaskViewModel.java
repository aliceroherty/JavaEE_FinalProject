/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.viewmodels;

import com.ats.models.ITask;
import java.util.List;

/**
 *
 * @author soake
 */
public class EditTaskViewModel {
    private List<ITask> tasks;
    private ITask task;
    
    public List<ITask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks;
    }

    public ITask getTask() {
        return task;
    }

    public void setTask(ITask task) {
        this.task = task;
    }


}
