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
                <c:forEach var="employee" items="${vm.employees}">
                    <a data-toggle="modal" data-target="#employee${employee.getId()}"/>
                    <div class="card">
                        <div class="card-body">
                            <h1><c:out value="${employee.getFirstName()} ${employee.getLastName()}"/></h1>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </c:if>
    </div>
    <c:if test="${vm.getEmployees().size() > 0}">
        <c:forEach var="employee" items="${vm.getEmployees()}">                 
            <div class="modal fade" id="employee${employee.getId()}" tabindex="-1" role="dialog">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title" id="exampleModalLongTitle">${employee.getFirstName()} ${employee.getLastName()}</h1>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div id="topContainer">
                                <div id="idContainer">
                                    <h3 class="title">ID:</h3>
                                    <h3 class="title">${employee.getId()}</h3>
                                </div>
                                <div id="durationContainer">
                                    <h3 class="title">SIN:</h3>
                                    <h3>${employee.getSIN()}</h3>
                                </div>
                            </div>
                            <div id="descriptionContainer">
                                <h3 class="title">Hourly Rate:</h3>
                                <h3>${employee.getHourlyRate()}</h3>
                            </div>
                            <c:if test="${employee.isDeleted == true}">
                            <div id="descriptionContainer">
                                <h3 class="title">Deleted:</h3>
                                <h3>Yes</h3>
                            </div>
                            </c:if>
                            <div id="descriptionContainer">
                                <h3 class="title">Created:</h3>
                                <h3>${employee.getCreatedAt()}</h3>
                            </div>
                            
                            <div id="descriptionContainer">
                                <h3 class="title">Updated:</h3>
                                <h3>${employee.getUpdatedAt()}</h3>
                            </div>
                            <div id="descriptionContainer">
                                <h3 class="title">Deleted:</h3>
                                <h3>${employee.getDeletedAt()}</h3>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Update</button>
                            <button type="button" class="btn btn-danger">Delete</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>
