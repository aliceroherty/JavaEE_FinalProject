<%-- 
    Document   : jobs
    Created on : 17-Mar-2020, 11:10:33 PM
    Author     : alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/jspf/head.jspf" %>
    <body id="viewJobs">
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <h1 class="mt-3">Jobs</h1>
        <form class="searchForm" method="post">
            <input type="date" name="search" class="form-control"/>
            <button type="submit" class="btn btn-outline-primary"><span><i class="fas fa-search"></i></span></button>
        </form>
        <c:if test="${message != null}">
            <h2 class="text-center"><c:out value="${message}"/></h2>
        </c:if>
        <c:if test="${vm.getJobs().size() > 0}">
            <div class="table-responsive mx-auto">
                <table class="table table-light table-bordered table-stripped table-dark text-center">
                    <thead>
                        <tr>
                            <th>Teams</th>
                            <c:forEach var="time" items="${vm.getTimes()}">
                                <th>${time}</th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="team" items="${vm.getTeams()}">
                            <tr>
                                <td>${team.getName()}</td>
                                <c:set var="offset" value="0"/>
                                <c:forEach var="time" items="${vm.getTimes()}">
                                    <c:set var="hasJob" value="false"/>
                                    <c:forEach var="job" items="${vm.getJobs().stream().filter(j -> j.getTeam().getId() == team.getId()).toArray()}">
                                        <c:if test="${vm.getTime(job.getStartTime()).equals(time)}">
                                            <c:set var="i" value="0"/>
                                            <c:set var="cols" value="1"/>
                                            <c:set var="hasPassedStartTime" value="false"/>
                                            <c:forEach var="t" items="${vm.getTimes()}">
                                                <c:if test="${vm.getTime(job.getStartTime()).equals(t)}">
                                                    <c:set var="hasPassedStartTime" value="true"/>
                                                </c:if>
                                                <c:if test="${vm.getTime(job.getEndTime()).equals(t)}">
                                                    <c:set var="cols" value="${i}" />
                                                </c:if>
                                                <c:if test="${hasPassedStartTime}">
                                                    <c:set var="i" value="${i + 1}"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:set var="hasJob" value="true"/>
                                            <td class="job" colspan="${cols}"><a data-toggle="modal" data-target="#job${job.getId()}"></a>${job.getClientName()}</td>
                                            <c:set var="offset" value="${cols - 1}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${!hasJob}">
                                        <c:choose>
                                            <c:when test="${offset == 0}">
                                                <td></td>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="offset" value="${offset - 1}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
            
        <c:if test="${vm.getJobs().size() > 0}">
            <c:forEach var="job" items="${vm.getJobs()}">                 
                <div class="modal fade" id="job${job.getId()}" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title">${job.getClientName()}</h1>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="topContainer">
                                    <div id="costContainer">
                                        <h3 class="title">Cost:</h3>
                                        <h3><fmt:formatNumber type="currency" value="${job.getCost()}"/></h3>
                                    </div>
                                    <div id="revenueContainer">
                                        <h3 class="title">Revenue:</h3>
                                        <h3><fmt:formatNumber type="currency" value="${job.getRevenue()}"/></h3>
                                    </div>
                                </div>
                                <div class="topContainer">
                                    <div id="startTimeContainer">
                                        <h3 class="title">Start Time:</h3>
                                        <h3><fmt:formatDate pattern="MMMMM dd yyyy, h:mm a" value="${job.getStartTime()}"/></h3>
                                    </div>
                                </div>
                                <div class="topContainer">
                                    <div id="endTimeContainer">
                                        <h3 class="title">End Time:</h3>
                                        <h3><fmt:formatDate pattern="MMMMM dd yyyy, h:mm a" value="${job.getEndTime()}"/></h3>
                                    </div>
                                </div>
                                <div class="descriptionContainer">
                                    <h3 class="title">Description:</h3>
                                    <h3>${job.getDescription()}</h3>
                                </div>
                                <div class="topContainer">
                                    <div id="teamContainer">
                                        <h3 class="title">Team:</h3>
                                        <h3>${job.getTeam().getName()}</h3>
                                    </div>
                                </div>
                                <div class="topContainer">
                                    <div id="teamMemberContainer">
                                        <h3 class="title">Team Members:</h3>
                                        <ul>
                                            <c:forEach var="employee" items="${job.getTeam().getEmployees()}">
                                                <li>${employee.getFirstName()} ${employee.getLastName()}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div id="taskContainer">
                                        <h3 class="title">Tasks:</h3>
                                        <ul>
                                            <c:forEach var="task" items="${job.getTasks()}">
                                                <li>${task.getName()}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
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
