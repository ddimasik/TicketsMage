<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Tickets Mage</title>

	<meta charset="UTF-8">

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/allTrains" var="urlAllTrains" />
<spring:url value="/trains/add" var="urlAddTrain" />
<spring:url value="/stations/add" var="urlAddStation" />
<spring:url value="/trains/search" var="urlSearchTrain" />


<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Home</a>
		</div>
		<div id="navbarTrains">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${urlAllTrains}">All trains</a></li>
			</ul>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${urlAddTrain}">Add train</a></li>
			</ul>
			</ul>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${urlSearchTrain}">Search train</a></li>
			</ul>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddStation}">Add Station</a></li>
			</ul>
		</div>
	</div>
</nav>