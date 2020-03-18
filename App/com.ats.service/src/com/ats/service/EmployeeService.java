package com.ats.service;

import com.ats.models.ErrorFactory;
import com.ats.models.IEmployee;
import com.ats.models.ITask;
import com.ats.repo.IEmployeeRepo;
import com.ats.repo.RepoFactory;
import java.util.Date;
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
        if (employee.getFirstName().equals("")) {
            addError(employee, "First Name is required.");
        } 
        
        if (employee.getLastName().equals("")) {
            addError(employee, "Last Name is required.");
        }
        
        if (employee.getSIN() == 0) {
            addError(employee, "SIN is required.");
        } else if (Integer.toString(employee.getSIN()).length() != 9) {
            addError(employee, "SIN must be 9 digits.");
        }
        
        if (employee.getHourlyRate() == 0) {
            addError(employee, "Hourly Rate is required.");
        }
        
        return isValid(employee) ? repo.insertEmployee(employee) : 0;        
    }

    @Override
    public int updateEmployee(IEmployee employee) {
        employee.setUpdatedAt(new Date());
        return repo.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        int numTeams = getNumberOfTeams(id);
        if (numTeams != 0) {
            IEmployee employee = getEmployee(id);
            employee.setDeleted(true);
            employee.setDeletedAt(new Date());
            return repo.updateEmployee(employee);
        } else {
            return repo.deleteEmployee(id);
        }
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
        return employee.getErrors().size() == 0;
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

    @Override
    public int getNumberOfTeams(int id) {
        return repo.getNumberOfTeams(id);
    }

    @Override
    public void addError(IEmployee employee, String message) {
        employee.addError(ErrorFactory.createInstance(message));
    }

    @Override
    public List<ITask> getSkills(int employeeID) {
        return repo.getSkills(employeeID);
    }
}
