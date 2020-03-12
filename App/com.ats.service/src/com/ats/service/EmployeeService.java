package com.ats.service;

import com.ats.models.IEmployee;
import com.ats.repo.IEmployeeRepo;
import com.ats.repo.RepoFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Service Class
 *
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

    @Override
    public boolean isValid(IEmployee employee) {
        return employee.getErrors().size() > 0;
    }

    @Override
    public List<IEmployee> searchEmployees(String searchText) {
        List<IEmployee> results = repo.getEmployees();

        try {
            int sin = Integer.parseInt(searchText);
            results = results.stream().filter(employee -> employee.getSIN() == sin).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            results = results.stream().filter(employee -> employee.getLastName().equals(searchText)).collect(Collectors.toList());
        }

        return results;
    }

}
