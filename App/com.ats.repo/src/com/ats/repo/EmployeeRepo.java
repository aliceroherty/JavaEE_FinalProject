package com.ats.repo;

import com.ats.models.IEmployee;
import com.ats.dataaccess.*;
import com.ats.models.EmployeeFactory;
import com.ats.models.ITask;
import com.ats.models.TaskFactory;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 * Employee Repository Class
 *
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class EmployeeRepo extends BaseRepo implements IEmployeeRepo {

    private IDAL db = DALFactory.createInstance();

    /**
     * Creates an Employee.
     *
     * @param employee The Employee to be created.
     * @return The number of rows affected.
     */
    @Override
    public int insertEmployee(IEmployee employee) {
        int id = 0;
        List<Object> returnParams;
        List<IParameter> params = ParameterFactory.createListInstance();

        IParameter idParam = ParameterFactory.createInstance(id, IParameter.Direction.OUT, Types.INTEGER);
        params.add(idParam);

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

        returnParams = db.executeNonQuery("CALL Employee_Insert(?, ?, ?, ?, ?, ?, ?, ?, ?);", params);

        try {
            if (returnParams != null && !returnParams.isEmpty()) {
                id = Integer.parseInt(returnParams.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public int insertEmployeeTask(int TaskID, int EmpID) {
        int id = 0;
        List<Object> returnParams;
        List<IParameter> params = ParameterFactory.createListInstance();

        IParameter TaskIDParam = ParameterFactory.createInstance(TaskID, IParameter.Direction.IN, Types.INTEGER);
        params.add(TaskIDParam);

        IParameter EmpIDParam = ParameterFactory.createInstance(EmpID, IParameter.Direction.IN, Types.INTEGER);
        params.add(EmpIDParam);

        returnParams = db.executeNonQuery("CALL EmployeeTask_Insert(?, ?);", params);

        try {
            if (returnParams != null && !returnParams.isEmpty()) {
                id = Integer.parseInt(returnParams.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }


@Override
        public int updateEmployee(IEmployee employee) {
        int rowsAffected = 0;
        List<Object> returnValues;
        List<IParameter> params = ParameterFactory.createListInstance();

        params.add(ParameterFactory.createInstance(employee.getId()));
        params.add(ParameterFactory.createInstance(employee.getFirstName()));
        params.add(ParameterFactory.createInstance(employee.getLastName()));
        params.add(ParameterFactory.createInstance(employee.getSIN()));
        params.add(ParameterFactory.createInstance(employee.getHourlyRate()));
        params.add(ParameterFactory.createInstance(employee.isDeleted()));
        params.add(ParameterFactory.createInstance(employee.getCreatedAt()));
        params.add(ParameterFactory.createInstance(employee.getUpdatedAt()));
        params.add(ParameterFactory.createInstance(employee.getDeletedAt()));

        returnValues = db.executeNonQuery("CALL Employee_Update(?, ?, ?, ?, ?, ?, ?, ?, ?);", params);

        try {
            if (returnValues != null) {
                rowsAffected = Integer.parseInt(returnValues.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rowsAffected;
    }

    @Override
        public int deleteEmployee(int id) {
        int rowsAffected = 0;
        List<Object> returnValues;

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(id));

        returnValues = db.executeNonQuery("CALL Employee_Delete(?);", params);

        try {
            if (returnValues != null && !returnValues.isEmpty()) {
                rowsAffected = Integer.parseInt(returnValues.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rowsAffected;
    }

    /**
     * Retrieves All Employees
     *
     * @return A List containing all of the Employees retrieved from the
     * Database.
     */
    @Override
        public List<IEmployee> getEmployees() {
        List<IEmployee> employees = EmployeeFactory.createListInstance();

        CachedRowSet results = db.executeFill("CALL Employee_GetAll();", ParameterFactory.createListInstance());

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
                employee.setSkills(getSkills(getInt("ID", results)));

                employees.add(employee);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return employees;
    }

    @Override
        public IEmployee getEmployee(int id) {
        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(id));
        CachedRowSet results = db.executeFill("CALL Employee_GetByID(?);", params);
        IEmployee employee = EmployeeFactory.createInstance();

        try {
            if (results.next()) {
                employee.setId(getInt("ID", results));
                employee.setFirstName(getString("FirstName", results));
                employee.setLastName(getString("LastName", results));
                employee.setSIN(getInt("SIN", results));
                employee.setHourlyRate(getDouble("HourlyRate", results));
                employee.setDeleted(getBoolean("isDeleted", results));
                employee.setCreatedAt(getDate("CreatedAt", results));
                employee.setUpdatedAt(getDate("UpdatedAt", results));
                employee.setDeletedAt(getDate("DeletedAt", results));
                employee.setSkills(getSkills(getInt("ID", results)));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return employee;
    }

    @Override
        public int getNumberOfTeams(int id) {
        int numTeams = 0;
        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(id));

        try {
            numTeams = Integer.parseInt(db.executeScalar("CALL Employee_GetNumberOfTeams(?);", params).toString());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return numTeams;
    }

    @Override
        public List<ITask> getSkills(int employeeID) {
        List<ITask> skills = TaskFactory.createListInstance();

        List<IParameter> params = ParameterFactory.createListInstance();
        params.add(ParameterFactory.createInstance(employeeID));

        CachedRowSet results = db.executeFill("CALL Employee_GetSkills(?);", params);

        try {
            while (results.next()) {
                ITask skill = TaskFactory.createInstance(
                        getInt("ID", results),
                        getString("Name", results),
                        getString("Description", results),
                        getInt("Duration", results)
                );

                skills.add(skill);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return skills;
    }

}
