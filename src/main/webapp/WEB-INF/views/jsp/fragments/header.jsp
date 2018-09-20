<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<head>
<title>Tickets Mage</title>

	<meta charset="UTF-8">

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/stations" var="urlAllStations" />
<spring:url value="/allTrains" var="urlAllTrains" />
<spring:url value="/trains/add" var="urlAddTrain" />
<spring:url value="/stations/add" var="urlAddStation" />
<spring:url value="/trains/search" var="urlSearchTrain" />
<spring:url value="/login" var="urlLogin" />
<spring:url value="/logout" var="urlLogout" />




<nav class="navbar" style="background-color: #E7C697; font-size: large; border: none; ">
    <div class="container">
        <div id="navbar">
            <ul class="nav navbar-nav">
                <li><a href="${urlSearchTrain}">Search train</a></li>
                <li><a href="${urlAllStations}">All stations</a></li>
                <li><a href="${urlAllTrains}">All trains</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize ifAnyGranted="ROLE_ANONYMOUS">
                    <li><a href="${urlLogin}">Staff</a></li>
                </security:authorize>
                <security:authorize ifAnyGranted="ROLE_ADMIN">
                    <li><a href="${urlAddTrain}">Add train</a></li>
                    <li><a href="${urlAddStation}">Add station</a></li>
                    <li><a href="${urlLogout}">Logout</a></li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>

