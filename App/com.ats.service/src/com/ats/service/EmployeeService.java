package com.ats.service;

import com.ats.models.IEmployee;
import com.ats.repo.IEmployeeRepo;
import com.ats.repo.RepoFactory;
import java.util.List;

/**
 * Employee Service Class
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public class EmployeeService implements IEmployeeService {
    private IEmployeeRepo repo = RepoFactory.createEmployeeInstance();
    
    @Override
    public int insertEmployee(IEmployee employee) {
        return repo.insertEmployee(employee);
    }

    @Override
    public int updateEmployee(IEmployee employee) {
        return repo.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        return repo.deleteEmployee(id);
    }

    @Override
    public List<IEmployee> getEmployees() {
        return repo.getEmployees();
    }

    @Override
    public IEmployee getEmployee(int id) {
        return repo.getEmployee(id);
    }
    
}
