<%-- 
    Document   : createTeam
    Created on : 7-Mar-2020, 1:01:59 AM
    Author     : soake
--%>
<%@page import="com.ats.viewmodels.CreateTeamViewModel"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Create Team</h1>
        <form method="POST" class="mx-auto mt-4">
            <input type="text" name="name" placeholder="Name" class="form-control text-center"/>
            <select class="form-control" name="member1">
                <option>
                    Select 1st Team Member
                </option>
            </select>
            <select class="form-control" name="member2">
                <option>
                    Select 2nd Team Member
                </option>
                <c:forEach var="employee" items="${vm.employees}">
                    <option value="${employee.getId()}">${employee.getFullName()}
                    </option>
                </c:forEach>    
            </select>
            <div class="form-check">
                <input type="checkbox" class="checkbox" name="onCall" id="onCall">
                <label class="form-check-label" for="onCall">On Call</label>
            </div>

            <button type="submit" class="btn btn-primary">Create</button>
        </form>
        <c:if test="${message != null}">
            <h2><c:out value="${message}"/></h2>
        </c:if>
    </body>
</html>
