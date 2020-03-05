package com.ats.controllers;

import com.ats.service.IEmployeeService;
import com.ats.service.ServiceFactory;
import com.ats.viewmodels.EmployeeListViewModel;
import java.io.IOException;
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
                    break;
                case "createEmployee":
                    super.setView(request, CREATE_EMPLOYEE);
                    break;
            }
        }
        
        super.getView().forward(request, response);
    }
}
