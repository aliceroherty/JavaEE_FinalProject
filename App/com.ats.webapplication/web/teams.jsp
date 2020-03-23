<%-- 
    Document   : teams
    Created on : 23-Mar-2020, 6:42:06 PM
    Author     : Alice Roherty-Carrier
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="text-center mt-3">Teams</h1>
        <div class="teamContainer">
            <%-- Generating Cards --%>
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
        
        <%-- Generating Modals --%>
        <c:if test="${vm.getTeams().size() > 0}">
            <c:forEach var="team" items="${vm.getTeams()}">                 
                <div class="modal fade" id="team${team.getId()}" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title">${team.getName()} <c:if test="${team.isDeleted()}"><c:out value="- Deleted"/></c:if></h1>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="topContainer">
                                    <div id="isOnCallContainer">
                                        <h3 class="title">On Call:</h3>
                                        <h3><c:choose><c:when test="${team.isOnCall()}"><c:out value="Yes"/></c:when><c:otherwise><c:out value="No"/></c:otherwise></c:choose></h3>
                                    </div>
                                    <div class="dateContainer">
                                        <h3 class="title">Created At:</h3>
                                        <h3><fmt:formatDate pattern="MMMMM dd yyyy, h:mm a" value="${team.getCreatedAt()}"/></h3>
                                    </div>
                                </div>
                                <div class="topContainer">
                                    <div class="teamMemberContainer">
                                        <h3 class="title">Team Members:</h3>
                                        <ul>
                                            <c:forEach var="employee" items="${team.getEmployees()}">
                                                <li>${employee.getFirstName()} ${employee.getLastName()}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <c:if test="${team.getUpdatedAt() != null || team.getDeletedAt() != null}">
                                    <div class="descriptionContainer">
                                        <c:if test="${team.getUpdatedAt() != null}">
                                            <div class="dateContainer" id="updatedAtContainer">
                                                <h3 class="title">Updated At:</h3>
                                                <h3><fmt:formatDate pattern="MMMMM dd yyyy, h:mm a" value="${team.getUpdatedAt()}"/></h3>
                                            </div>
                                        </c:if>
                                        <c:if test="${team.getDeletedAt() != null}">
                                            <div class="dateContainer">
                                                <h3 class="title">Deleted At:</h3>
                                                <h3><fmt:formatDate pattern="MMMMM dd yyyy, h:mm a" value="${team.getDeletedAt()}"/></h3>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:if>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Update</button>
                                <button type="button" class="btn btn-danger" onclick="deleteTask(${task.getId()})">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </body>
</html>
