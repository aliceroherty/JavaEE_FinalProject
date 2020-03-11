<%-- 
    Document   : createTeam
    Created on : 7-Mar-2020, 1:01:59 AM
    Author     : soake
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body id="createTeam">
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Create Team</h1>
        <form method="POST" class="mx-auto mt-4">
            <input type="text" name="name" placeholder="Name" class="form-control text-center"/>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" name="onCall" id="onCall">
                <label class="form-check-label" for="onCall">On Call</label>
            </div>
            <div>
                <select name="member1">
                    <option value="categoryID">
                        
                    </option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary submitButton">Create</button>
        </form>
        <c:if test="${message != null}">
            <h2><c:out value="${message}"/></h2>
        </c:if>
    </body>
</html>
