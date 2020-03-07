package com.ats.viewmodels;

import com.ats.models.IEmployee;
import com.ats.models.ITeam;
import java.util.List;

/**
 * Employee List View Model
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public class EmployeeListViewModel {
    private List<IEmployee> employees;

    public List<IEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<IEmployee> employees) {
        this.employees = employees;
    }
}
