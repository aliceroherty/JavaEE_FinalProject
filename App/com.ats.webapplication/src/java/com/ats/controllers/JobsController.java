package com.ats.controllers;

import com.ats.service.IJobService;
import com.ats.service.ITaskService;
import com.ats.service.ITeamService;
import com.ats.service.ServiceFactory;
import com.ats.viewmodels.CreateJobViewModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Job Controller Class
 *
 * @author Alice Roherty-Carrier
 * @date 12-03-2020
 */
public class JobsController extends CommonController {
    IJobService service = ServiceFactory.createJobInstance();
    ITaskService taskService = ServiceFactory.createTaskInstance();
    ITeamService teamService = ServiceFactory.createTeamInstance();

    private final String CREATE_JOB = "/createJob.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[(pathParts.length - 1)];

            switch (action) {
                case "createJob":
                    CreateJobViewModel vm = new CreateJobViewModel();
                    vm.setSkills(taskService.getTasks());
                    vm.setTeams(teamService.getTeams());
                    super.setView(request, CREATE_JOB);
                    break;
            }

            super.getView().forward(request, response);
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
                    case "createJob":

                        super.setView(request, CREATE_JOB);

                        break;
                }

                super.getView().forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
