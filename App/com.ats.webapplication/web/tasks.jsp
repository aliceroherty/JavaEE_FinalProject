<%-- 
    Document   : tasks
    Created on : 8-Mar-2020, 10:15:56 PM
    Author     : Alice R0herty-Carrier
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body id="viewTasks">
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Tasks</h1>
        <div class="taskContainer">
            <c:if test="${vm.getTasks().size() > 0}">
                <c:forEach var="task" items="${vm.getTasks()}">
                    <a data-toggle="modal" data-target="#task${task.getId()}">
                        <div class="card">
                            <div class="card-body">
                                <h1><c:out value="${task.getName()}"/></h1>
                                <h3><c:out value="${task.getDescription()}"/></h3>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </c:if>
        </div>

        <c:if test="${vm.getTasks().size() > 0}">
            <c:forEach var="task" items="${vm.getTasks()}">                 
                <div class="modal fade" id="task${task.getId()}" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title">${task.getName()}</h1>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="topContainer">
                                    <div id="idContainer">
                                        <h3 class="title">ID:</h3>
                                        <h3 class="title">${task.getId()}</h3>
                                    </div>
                                    <div id="durationContainer">
                                        <h3 class="title">Duration:</h3>
                                        <h3>${task.getDuration()} Minutes</h3>
                                    </div>
                                </div>
                                <div id="descriptionContainer">
                                    <h3 class="title">Description:</h3>
                                    <h3>${task.getDescription()}</h3>
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
