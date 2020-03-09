<%-- 
    Document   : employees
    Created on : 4-Mar-2020, 7:32:33 PM
    Author     : Alice Roherty-Carrier
--%>

<%@page import="com.ats.viewmodels.EmployeeListViewModel"%>
<%@page import="com.ats.models.IEmployee"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Employees</h1>
        <div class="employeeContainer">
            <c:if test="${vm.getEmployees().size() > 0}">
                <c:forEach var="employee" items="${vm.getEmployees()}">
                    <div class="card">
                        <div class="card-body">
                            <h1><c:out value="${employee.getFirstName()} ${employee.getLastName()}"/></h1>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
