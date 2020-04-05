package com.ats.controllers;

import com.ats.models.IEmployee;
import com.ats.models.ITask;
import com.ats.models.TaskFactory;
import com.ats.service.IEmployeeService;
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
 *
 * @author Alice Roherty-Carrier
 * @date 03-08-20
 */
public class TasksController extends CommonController {

    private ITaskService service = ServiceFactory.createTaskInstance();

    private static final String TASKS = "/tasks.jsp";
    private static final String CREATE_TASK = "/createTask.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[2];

            switch (action) {
                case "tasks":
                    TaskListViewModel vm = new TaskListViewModel();
                    vm.setTasks(service.getTasks());
                    request.setAttribute("vm", vm);
                    super.setView(request, TASKS);
                    break;
                case "createTask":
                    super.setView(request, CREATE_TASK);
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
                    case "tasks":
                        TaskListViewModel vm = new TaskListViewModel();
                        vm.setTasks(service.getTasks());
                        request.setAttribute("vm", vm);
                        super.setView(request, TASKS);
                        break;
                    case "createTask":
                        ITask task = setTask(request);
                        task.setId(service.insertTask(task));

                        if (!service.isValid(task)) {
                            request.setAttribute("errors", task.getErrors());
                        }

                        if (task.getId() > 0) {
                            request.setAttribute("message", "Task Created Successfully");
                        }
                        break;
                    case "deleteTask":
                        int id = getInteger(request, "id");
                        int rowsAffected = 0;
                        if (id != 0) {
                            rowsAffected = service.deleteTask(id);
                        }
                        response.getWriter().print(rowsAffected);
                        System.out.println(rowsAffected);
                        break;
                    case "deleteEmpTask":{

                        int empId = getInteger(request, "empID");
                        int taskId = getInteger(request, "taskID");

                        service.deleteEmpTask(empId, taskId);
                    }
                        break;

                    case "insertEmpTask":{
                        IEmployeeService empService = ServiceFactory.createEmployeeInstance();
                        
                        int empId = getInteger(request, "empID");
                        int taskId = getInteger(request, "taskID");
                        
                        empService.insertEmployeeTask(taskId, empId);                        
                        
                    }
                        break;
                    case "editTask":{
                        id = getInteger(request, "id");
                        
                        task = setTask(request);
                        task.setId(id);
                        
                        service.updateTask(task);
                        
                        if (service.isValid(task)) {
                            request.setAttribute("errors", task.getErrors());
                        }
                    
                    }
                        break;
                }
                if (!"deleteTask".equals(action)) {
                    super.getView().forward(request, response);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ITask getTask(HttpServletRequest request) {
        return TaskFactory.createInstance();
    }

    private ITask setTask(HttpServletRequest request) {
        String name = super.getValue(request, "name");
        String description = super.getValue(request, "description");
        int duration = super.getInteger(request, "duration");

        return TaskFactory.createInstance(name, description, duration);
    }
}
