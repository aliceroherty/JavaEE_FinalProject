/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.controllers;

import com.ats.models.IEmployee;
import com.ats.models.ITeam;
import com.ats.models.TeamFactory;
import com.ats.service.IEmployeeService;
import com.ats.service.ITeamService;
import com.ats.service.ServiceFactory;
import com.ats.viewmodels.CreateTeamViewModel;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.sin;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ats.models.EmployeeFactory;
import com.ats.viewmodels.TeamListViewModel;

/**
 *
 * @author soake
 */
public class TeamController extends CommonController {

    private ITeamService service = ServiceFactory.createTeamInstance();

    private static final String CREATE_TEAM = "/createTeam.jsp";
    private static final String TEAMS = "/teams.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[(pathParts.length - 1)];
            
            switch (action) {
                case "createTeam":
                    CreateTeamViewModel vm = new CreateTeamViewModel();
                    vm.setEmployee(getEmployees());
                    request.setAttribute("vm", vm);
                    super.setView(request, CREATE_TEAM);
                    break;
                case "teams":
                    TeamListViewModel tlvm = new TeamListViewModel(service.getTeams());
                    request.setAttribute("vm", tlvm);
                    super.setView(request, TEAMS);
                    break;
            }
        }

        super.getView().forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getRequestURI();

            if (path != null) {
                String[] pathParts = path.split("/");
                String action = pathParts[(pathParts.length - 1)];

                switch (action) {
                    case "createTeam":
                        ITeam team = setTeam(request);
                        team.setId(service.insertTeam(team));

                        if (!service.isValid(team)) {
                            request.setAttribute("errors", team.getErrors());
                        }

                        if (team.getId() > 0) {
                            request.setAttribute("message", "Team Created Successfully");
                        }

                        super.setView(request, CREATE_TEAM);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        super.getView().forward(request, response);

    }
    
    private List<IEmployee> getEmployees(){
        IEmployeeService empService = ServiceFactory.createEmployeeInstance();
        List<IEmployee> employees = empService.getEmployees();
        
        return employees;       
    }

    private ITeam setTeam(HttpServletRequest request) {
        IEmployeeService employeeService = ServiceFactory.createEmployeeInstance();
        
        String name = super.getValue(request, "name");
        boolean isOnCall = request.getParameter("onCall") != null;
        Date createdAt = new Date();
        Date updatedAt = null;
        boolean isDeleted = false;
        Date deletedAt = null;
        List<IEmployee> employees = EmployeeFactory.createListInstance();
        
        employees.add(employeeService.getEmployee(getInteger(request, "member1")));
        employees.add(employeeService.getEmployee(getInteger(request, "member2")));
        
        
        return TeamFactory.createInstance(name, isOnCall, createdAt, updatedAt, isDeleted, deletedAt, employees);
    }
}
