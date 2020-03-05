package com.ats.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Home Controller 
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public class HomeController extends CommonController {
    private static final String HOME = "/index.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setView(request, HOME);
        super.getView().forward(request, response);
    }
}
