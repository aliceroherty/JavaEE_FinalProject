<%-- 
    Document   : createEmployee
    Created on : 4-Mar-2020, 7:39:29 PM
    Author     : Alice Roherty-Carrier
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body id="createEmployee">
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Create Employee</h1>
        <form method="POST" class="mx-auto mt-4">
            <input type="text" name="firstName" placeholder="First Name" class="form-control text-center"/>
            <input type="text" name="lastName" placeholder="Last Name" class="form-control text-center"/>
            <input type="text" name="sin" placeholder="SIN" class="form-control text-center"/>
            <input type="text" name="hourlyRate" placeholder="Hourly Rate" class="form-control text-center"/>
            <button type="submit" class="btn btn-primary submitButton">Create</button>
        </form>
        <c:if test="${message != null}">
            <h2><c:out value="${message}"/></h2>
        </c:if>
    </body>
</html>
