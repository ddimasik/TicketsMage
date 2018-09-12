<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Search result</h2>
        <table class="table table-striped">
            <th><h4>Train</h4></th><th><h4>Buy ticket</h4></th>
            <c:forEach var="train" items="${searchResult}">
                <tr>
                    <td>${train.name}</td>
                    <td>
                        <form action="/trains/bookTicket/${train.id}" method="POST">
                            <div>
                                <input name="startStationId"  value="${searchDTO.startStationId}">
                                <input name="endStationId"  value="${searchDTO.endStationId}">
                                <input name="startDateTime"  value="${searchDTO.startDateTime}">
                                <input name="endDateTime"  value="${searchDTO.endDateTime}">
                            </div>
                            <button type='submit' name='openTicketForm'>Open ticket form</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


