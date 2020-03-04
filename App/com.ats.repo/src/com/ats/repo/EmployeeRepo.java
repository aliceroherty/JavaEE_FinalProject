package com.ats.repo;

import com.ats.models.IEmployee;
import com.ats.dataaccess.*;
import com.ats.models.EmployeeFactory;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 * Employee Repository Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class EmployeeRepo extends BaseRepo implements IEmployeeRepo {
    private IDAL db = DALFactory.createInstance();

    /**
     * Creates an Employee.
     * @param employee The Employee to be created.
     * @return The number of rows affected.
     */
    @Override
    public int insertEmployee(IEmployee employee) {
        List<IParameter> params = ParameterFactory.createListInstance();
        
        IParameter firstName = ParameterFactory.createInstance(employee.getFirstName(), IParameter.Direction.IN, Types.VARCHAR);
        params.add(firstName);
        
        IParameter lastName = ParameterFactory.createInstance(employee.getLastName(), IParameter.Direction.IN, Types.VARCHAR);
        params.add(lastName);
        
        IParameter sin = ParameterFactory.createInstance(employee.getSIN(), IParameter.Direction.IN, Types.INTEGER);
        params.add(sin);
        
        IParameter hourlyRate = ParameterFactory.createInstance(employee.getHourlyRate(), IParameter.Direction.IN, Types.DOUBLE);
        params.add(hourlyRate);
        
        IParameter isDeleted = ParameterFactory.createInstance(employee.isDeleted(), IParameter.Direction.IN, Types.BOOLEAN);
        params.add(isDeleted);
        
        IParameter createdAt = ParameterFactory.createInstance(employee.getCreatedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(createdAt);
        
        IParameter updatedAt = ParameterFactory.createInstance(employee.getUpdatedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(updatedAt);
        
        IParameter deletedAt = ParameterFactory.createInstance(employee.getDeletedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(deletedAt);
        
        return (int) db.executeNonQuery("Employee_Insert", params).get(0);
    }

    @Override
    public int updateEmployee(IEmployee employee) {
        return 0;
    }

    @Override
    public int deleteEmployee(int id) {
        return 0;
    }

    /**
     * Retrieves All Employees
     * @return A List containing all of the Employees retrieved from the Database.
     */
    @Override
    public List<IEmployee> getEmployees() {
        List<IEmployee> employees = EmployeeFactory.createListInstance();
        
        CachedRowSet results = db.executeFill("Employee_GetAll", ParameterFactory.createListInstance());
        
        try {
            while (results.next()) {
                IEmployee employee = EmployeeFactory.createInstance();
                
                employee.setId(getInt("ID", results));
                employee.setFirstName(getString("FirstName", results));
                employee.setLastName(getString("LastName", results));
                employee.setSIN(getInt("SIN", results));
                employee.setHourlyRate(getDouble("HourlyRate", results));
                employee.setDeleted(getBoolean("isDeleted", results));
                employee.setCreatedAt(getDate("CreatedAt", results));
                employee.setUpdatedAt(getDate("UpdatedAt", results));
                employee.setDeletedAt(getDate("DeletedAt", results));
                
                employees.add(employee);
            }
        } catch (Exception ex) {
            
        }
        
        return employees;
    }

    @Override
    public IEmployee getEmployee(int id) {
        return EmployeeFactory.createInstance();
    }
    
}
