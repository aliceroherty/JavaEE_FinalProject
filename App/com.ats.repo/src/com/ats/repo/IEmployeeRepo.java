package com.ats.repo;

import com.ats.models.IEmployee;
import com.ats.models.ITask;
import java.util.List;

/**
 * Employee Repository Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface IEmployeeRepo {
    int insertEmployee(IEmployee employee);
    int updateEmployee(IEmployee employee);
    int deleteEmployee(int id);
    List<IEmployee> getEmployees();
    IEmployee getEmployee(int id);
    int getNumberOfTeams(int id);
    List<ITask> getSkills(int employeeID);
    public int insertEmployeeTask(int TaskID, int EmpID);
}
