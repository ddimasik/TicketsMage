<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Search result</h2>
    <form:form method="POST" action="/trains/buyTicket">
        <div>
            <label>Name</label>
            <input name="name" required type="text"></div>
        </div>
        <div>
            <label>Surname</label>
            <input name="surname" required type="text"></div>
        </div>
        <div>
            <label>Birthday</label>
            <input name="birthday" required type="date"></div>
        </div>
        <div>
            <input name="ticketId"  value="${ticketId}">
        </div>
        <input type="submit" value="Buy ticket" >
    </form:form>
</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


