package com.ats.service;

import com.ats.models.IEmployee;
import java.util.List;

/**
 * Employee Service Interface
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public interface IEmployeeService {
    int insertEmployee(IEmployee employee);
    int updateEmployee(IEmployee employee);
    int deleteEmployee(int id);
    List<IEmployee> getEmployees();
    IEmployee getEmployee(int id);
    boolean isValid(IEmployee employee);
    List<IEmployee> searchEmployees(String searchText);
}
