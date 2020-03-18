<%-- 
    Document   : addEmployeeSkills
    Created on : 18-Mar-2020, 5:58:02 PM
    Author     : soake
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3 mb-0">Add Skills</h1>
        <c:if test="${errors != null && errors.size() > 0}">
            <ul class="alert alert-danger mx-auto px-5 mt-3">
                <c:forEach items="${errors}" var="error">
                    <li><c:out value="${error.getDescription()}"/></li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${message != null}">
            <h2 class="text-center mt-3"><c:out value="${message}"/></h2>
        </c:if>
        <form method="POST" class="mx-auto mt-4 mb-5">
            <div class="form-group">
                <label for="skills">Skills</label>
                <select name="skills" size="5" multiple class="form-control text-center">
                    <c:if test="${vm != null && vm.getSkills().size() > 0}">
                        <c:forEach items="${vm.getSkills()}" var="skill">
                            <option value="${skill.getId()}"><c:out value="${skill.getName()}"/></option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            
            <button type="submit" class="btn btn-primary submitButton mt-2">Create</button>
        </form>
    </body>
</html>
