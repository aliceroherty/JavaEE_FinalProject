/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.controllers;

import com.ats.models.ITeam;
import com.ats.models.TeamFactory;
import com.ats.service.ITeamService;
import com.ats.service.ServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.sin;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author soake
 */
public class TeamController extends CommonController {
    
    private ITeamService service = ServiceFactory.createTeamInstance();

    private static final String CREATE_TEAM = "/team.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path != null) {
            String[] pathParts = path.split("/");
            String action = pathParts[(pathParts.length - 1)];

            switch (action) {
                case "createTeam":
                    super.setView(request, CREATE_TEAM);
                    break;
            }
        }

        super.getView().forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    

                    super.setView(request, CREATE_TEAM);
                    break;
            }
        }

        super.getView().forward(request, response);

    }

    private ITeam setTeam(HttpServletRequest request) {
        String name = super.getValue(request, "name");
        boolean isOnCall = super.getBoolean(request, "onCall");        
        Date createdAt = new Date();
        Date updatedAt = null;
        boolean isDeleted = false;
        Date deletedAt = null;
        return TeamFactory.createInstance(name, isOnCall, createdAt, updatedAt, isDeleted, deletedAt);
    }
}
