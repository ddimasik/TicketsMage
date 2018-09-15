<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Search train</h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">Clear</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>


    <form:form method="GET" action="/trains/searchResult" modelAttribute="searchTrainFragment">
        <table class="table table-striped">
            <th></th><th>Station</th><th>Date & time</th>
            <tr>
                <td><h3>Start</h3></td>
                <td><form:select path="startStationId" multiple="">
                    <c:forEach var="station" items="${stations}">
                        <form:option value="${station.id}">${station.name}</form:option>
                    </c:forEach>
                </form:select>
                </td>
                <td><input name="startDateTime" type="datetime-local" required="required" "/></td>
            </tr>
            <tr>
                <td><h3>End</h3></td>
                <td><form:select path="endStationId" multiple="">
                    <c:forEach var="station" items="${stations}">
                        <form:option value="${station.id}">${station.name}</form:option>
                    </c:forEach>
                </form:select>
                </td>
                <td><input name="endDateTime" type="datetime-local" required="required" "/></td>
            </tr>
        </table>
        <input type="submit" value="Search">

    </form:form>
</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


