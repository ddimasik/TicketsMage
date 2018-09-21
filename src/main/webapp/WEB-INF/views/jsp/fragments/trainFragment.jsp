<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />



<div class="container">
    <h2>Add new train</h2>

        <c:if test="${not empty msg}">
            <div class="alert alert-${css} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"
                        aria-label="Close">
                    <span aria-hidden="true">Clear</span>
                </button>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <form:form method="POST" action="/allTrains" modelAttribute="trainFragment">
        <table class="table table-striped">
            <tr>
                <td><spring:message text="Name"/></td>
                <td><form:input path="name" required="required"/></td>
            </tr>
            <tr>
                <td><spring:message text="Capacity"/></td>
                <td><form:input path="capacity" type="number" required="required"/></td>
            </tr>

            <tr>
                <td><spring:message text="Start station"/></td>
                <td><form:select path="startSt" multiple="">
                    <c:forEach var="station" items="${stations}">
                        <form:option value="${station.id}">${station.name}</form:option>
                    </c:forEach>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td><spring:message text="Start date & time"/></td>
                    <td><input name="startDateTime" type="datetime-local"
                               min="2018-06-01T11:30" max="2018-12-30T11:59"
                               pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}"
                               required="required"/></td>
            </tr>
            <tr>
                <td colspan="2"><h3>Select stations and time</h3></td>
            </tr>

            <tr><th>Station</th><th>Minutes from start</th></tr>
            <c:forEach var="station" items="${stations}">
                <tr>
                    <td>
                        ${station.name}
                        <input hidden type="number" value="${station.id}" name="stationId" >
                    </td>
                    <td>
                        <input  type="number" name="minutesFromStartStn" value="0" required>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit">

    </form:form>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


