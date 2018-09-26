<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Here is your ticket!</h2>
        <div>
            <label>Ticket ID</label><div>${ticketDTO.id}</div>
        </div>
        <div>
            <label>trainId</label><div>${ticketDTO.trainId}</div>
        </div>
        <div>
            <label>passengerId</label><div>${ticketDTO.passengerId}</div>
        </div>
        <div>
            <label>startStationId</label><div>${ticketDTO.startStationId}</div>
        </div>
        <div>
            <label>endStationId</label><div>${ticketDTO.endStationId}</div>
        </div>
</div>

<jsp:include page="../fragments/footer.jsp" />

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


