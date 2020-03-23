<%-- 
    Document   : teams
    Created on : 23-Mar-2020, 6:42:06 PM
    Author     : Alice Roherty-Carrier
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="text-center mt-3">Teams</h1>
        <div class="teamContainer">
            <c:if test="${vm.getTeams().size() > 0}">
                <c:forEach var="team" items="${vm.getTeams()}">
                    <a data-toggle="modal" data-target="#team${team.getId()}">
                        <div class="card">
                            <div class="card-body">
                                <h1><c:out value="${team.getName()}"/></h1>
                                <c:forEach var="employee" items="${team.getEmployees()}">
                                    <h2><c:out value="${employee.getFirstName()} ${employee.getLastName()}"/></h2>
                                </c:forEach>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
