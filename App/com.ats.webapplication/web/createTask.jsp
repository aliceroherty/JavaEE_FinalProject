<%-- 
    Document   : createTask
    Created on : 9-Mar-2020, 2:01:04 AM
    Author     : soake
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body id="createTask">
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Create Task</h1>
        <c:if test="${errors != null && errors.size() > 0}">
            <ul class="alert alert-danger mx-auto px-5 mt-3">
                <c:forEach items="${errors}" var="error">
                    <li><c:out value="${error.getDescription()}"/></li>
                </c:forEach>
            </ul>
        </c:if>
        <form method="POST" class="mx-auto mt-4">
            <input type="text" name="name" placeholder="Name" class="form-control text-center" />
            <textarea name="description" placeholder="Description" class="form-control text-center"></textarea>
            <input type="text" name="duration" placeholder="Duration" class="form-control text-center"/>
            <button type="submit" class="btn btn-primary submitButton">Create</button>
        </form>
        <c:if test="${message != null}">
            <h2><c:out value="${message}"/></h2>
        </c:if>
    </body>
</html>

