<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Search the best train</h2>

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
                <td>
                    <input name="startDateTime" type="datetime-local" required="required"  value="${startDateTime}"
                           pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}">
                    <br>
                    <form:errors path="startDateTime" cssClass="alert-warning" />
                </td>
            </tr>
            <tr>
                <td><h3>End</h3></td>
                <td>
                    <form:select path="endStationId" multiple="">
                        <c:forEach var="station" items="${stations}">
                            <form:option value="${station.id}">${station.name}</form:option>
                        </c:forEach>
                    </form:select>
                    <br>
                    <form:errors path="endStationId" cssClass="alert-warning" />
                </td>
                <td>
                    <input name="endDateTime" type="datetime-local" required="required" value="${endDateTime}"
                           pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}"/>
                    <br>
                    <form:errors path="endDateTime" cssClass="alert-warning" />
                </td>
            </tr>
        </table>
        <input type="submit" value="Go went gone!"  class="btn-lg btn-primary">

    </form:form>
</div>

<jsp:include page="../fragments/footer.jsp" />

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


