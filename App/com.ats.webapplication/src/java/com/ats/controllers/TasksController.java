package com.ats.controllers;

import com.ats.models.IEmployee;
import com.ats.models.ITask;
import com.ats.models.TaskFactory;
import com.ats.service.ITaskService;
import com.ats.service.ServiceFactory;
import com.ats.viewmodels.EmployeeListViewModel;
import com.ats.viewmodels.TaskListViewModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tasks Controller
 * @author Alice Roherty-Carrier
 * @date 03-08-20
 */
public class TasksController extends CommonController {
    private ITaskService service = ServiceFactory.createTaskInstance();
    
    private static final String TASKS = "/tasks.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        String path = request.getRequestURI();
        
        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[(pathParts.length - 1)];
            
            switch (action) {
                case "tasks":
                    TaskListViewModel vm = new TaskListViewModel();
                    vm.setTasks(service.getTasks());
                    request.setAttribute("vm", vm);
                    super.setView(request, TASKS);
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
                        
                        break;
                }
            }

            super.getView().forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private ITask getTask(HttpServletRequest request) {
        return TaskFactory.createInstance();
    }
}
