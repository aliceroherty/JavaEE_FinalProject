package com.ats.controllers;

import com.ats.models.EmployeeFactory;
import com.ats.models.IEmployee;
import com.ats.service.IEmployeeService;
import com.ats.service.ServiceFactory;
import com.ats.viewmodels.EmployeeListViewModel;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Employees Controller
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public class EmployeesController extends CommonController {
    private IEmployeeService service = ServiceFactory.createEmployeeInstance();
    
    private static final String EMPLOYEES = "/employees.jsp";
    private static final String CREATE_EMPLOYEE = "/createEmployee.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        String path = request.getRequestURI();
        
        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[(pathParts.length - 1)];
            
            switch (action) {
                case "employees":
                    EmployeeListViewModel vm = new EmployeeListViewModel();
                    
                    vm.setEmployees(service.getEmployees());
                    
                    request.setAttribute("vm", vm);
                    super.setView(request, EMPLOYEES);
                    super.getView().forward(request, response);
                    break;
                case "createEmployee":
                    super.setView(request, CREATE_EMPLOYEE);
                    break;
            }
        }
        
        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        try {
            String path = request.getRequestURI();
        
            if (path != null) {
                String[] pathParts = path.split("/");
                String action = pathParts[(pathParts.length - 1)];

                switch (action) {
                    case "employees":
                        EmployeeListViewModel vm = new EmployeeListViewModel();
                        
                        String searchText = getValue(request, "search");
                        
                        if (!searchText.equals("")) {
                            vm.setEmployees(service.searchEmployees(searchText));
                        } else {
                            vm.setEmployees(service.getEmployees());
                        }
                        
                        request.setAttribute("vm", vm);
                        
                        if (vm.getEmployees().isEmpty()) {
                            request.setAttribute("message", "No Results");
                        }
                        
                        super.setView(request, EMPLOYEES);
                        super.getView().forward(request, response);
                        break;
                    case "createEmployee":
                        IEmployee employee = setEmployee(request);
                        employee.setId(service.insertEmployee(employee));

                        if (!service.isValid(employee)) {
                            request.setAttribute("errors", employee.getErrors());
                        }
                        
                        if (employee.getId() > 0) {
                            request.setAttribute("message", "Employee Created Successfully");
                        }

                        super.setView(request, CREATE_EMPLOYEE);
                        super.getView().forward(request, response);
                        break;
                    case "deleteEmployee":
                        int id = getInteger(request, "id");
                        int rowsAffected = 0;
                        if (id != 0) {
                            rowsAffected = service.deleteEmployee(id);
                        }
                        
                        System.out.println(rowsAffected);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private IEmployee setEmployee(HttpServletRequest request) {
        String firstName = super.getValue(request, "firstName");
        String lastName = super.getValue(request, "lastName");
        int sin = super.getInteger(request, "sin");
        double hourlyRate = super.getDouble(request, "hourlyRate");
        Date createdAt = new Date();
        boolean isDeleted = false;
        return EmployeeFactory.createInstance(firstName, lastName, sin, hourlyRate, createdAt, isDeleted);
    }
}
