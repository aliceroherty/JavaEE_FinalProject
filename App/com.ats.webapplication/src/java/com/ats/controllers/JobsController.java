package com.ats.controllers;

import com.ats.models.IJob;
import com.ats.models.ITask;
import com.ats.models.ITeam;
import com.ats.models.JobFactory;
import com.ats.models.TaskFactory;
import com.ats.service.IJobService;
import com.ats.service.ITaskService;
import com.ats.service.ITeamService;
import com.ats.service.ServiceFactory;
import com.ats.viewmodels.CreateJobViewModel;
import com.ats.viewmodels.JobListViewModel;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private final String JOBS = "/jobs.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[(pathParts.length - 1)];

            switch (action) {
                case "createJob":
                    CreateJobViewModel createJobViewModel = new CreateJobViewModel();
                    createJobViewModel.setSkills(taskService.getTasks());
                    createJobViewModel.setTeams(teamService.getTeams());
                    request.setAttribute("vm", createJobViewModel);
                    super.setView(request, CREATE_JOB);
                    break;
                case "jobs":
                    JobListViewModel jobListViewModel = new JobListViewModel();
                    jobListViewModel.setJobs(service.getJobs());
                    request.setAttribute("vm", jobListViewModel);
                    super.setView(request, JOBS);
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
                        IJob job = setJob(request);
                        job.setId(service.insertJob(job));
                        
                        if (!service.isValid(job)) {
                            request.setAttribute("errors", job.getErrors());
                        }
                        
                        if (job.getId() > 0) {
                            request.setAttribute("message", "Job Created Successfully");
                        }
                        
                        CreateJobViewModel vm = new CreateJobViewModel();
                        vm.setSkills(taskService.getTasks());
                        vm.setTeams(teamService.getTeams());
                        request.setAttribute("vm", vm);
                        
                        super.setView(request, CREATE_JOB);
                        break;
                }

                super.getView().forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private IJob setJob(HttpServletRequest request) {
        String description = super.getValue(request, "description");
        String clientName = super.getValue(request, "clientName");
        Date startTime = super.getDate(request, "startTime");
        
        Calendar c = Calendar.getInstance();
        
        if (startTime != null) {
            c.setTime(startTime);
        }
        
        List<ITask> skills = TaskFactory.createListInstance();
        
        if (request.getParameter("skills") != null) {
            int totalMinutes = 0;
            
            for (String skillID : request.getParameterValues("skills")) {
                int id = super.getInteger(skillID);
                ITask skill = taskService.getTask(id);
                skills.add(skill);

                //Adding task duration to end time
                totalMinutes += skill.getDuration();
            }
            
            if (startTime != null) {
                c.add(Calendar.MINUTE, totalMinutes);
            }
        }
        
        Date endTime = null;
                
        if (startTime != null) {
            endTime = c.getTime();
        }
        
        int id = super.getInteger(request, "team");
        ITeam team = teamService.getTeam(id);
        
        //Calculate Cost and Revenue
        double cost = 0;
        double revenue = 0;
        
        IJob job = JobFactory.createInstance(description, clientName, cost, revenue, startTime, endTime, skills, team);
        
        cost = service.calculateCost(job);
        revenue = service.calculateRevenue(job);
        
        job.setCost(cost);
        job.setRevenue(revenue);
        
        return job;
    }
}
