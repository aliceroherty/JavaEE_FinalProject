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
    private List<ITask> empTasks;
    private int empID;

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public List<ITask> getEmpTasks() {
        return empTasks;
    }

    public void setEmpTasks(List<ITask> empTasks) {
        this.empTasks = empTasks;
    }

    public List<ITask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks;
    }

}
