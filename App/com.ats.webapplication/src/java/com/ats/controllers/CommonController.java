package com.ats.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Common Controller
 * @author Alice Roherty-Carrier
 * @date 03-04-2020
 */
public class CommonController extends HttpServlet {

    private RequestDispatcher view;
    
    public RequestDispatcher getView(){
        return view;
    }
    
    public void setView(HttpServletRequest request,String viewPath){
        view = request.getRequestDispatcher(viewPath);
    }
        
    protected int getInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
    
    protected int getInteger(HttpServletRequest request, String key) {
        try {
            return Integer.parseInt(request.getParameter(key));
        } catch (Exception e) {
            return 0;
        }
    }

    protected double getDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    protected double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.parseDouble(request.getParameter(key));
        } catch (Exception e) {
            return 0.0;
        }
    }

    protected String getValue(HttpServletRequest request, String key) {
        return request.getParameter(key);       
    }

}