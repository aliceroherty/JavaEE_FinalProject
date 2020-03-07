<%-- 
    Document   : employees
    Created on : 4-Mar-2020, 7:32:33 PM
    Author     : Alice Roherty-Carrier
--%>

<%@page import="com.ats.viewmodels.EmployeeListViewModel"%>
<%@page import="com.ats.models.IEmployee"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    EmployeeListViewModel vm = (EmployeeListViewModel) request.getAttribute("vm");
    List<IEmployee> employees = vm.getEmployees();
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Employees</h1>
        <h3><%= employees.get(0).getFirstName() %></h3>
    </body>
</html>
