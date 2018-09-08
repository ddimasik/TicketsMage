<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Search result</h2>
    <form:form method="GET" action="/trains/buyTicket" modelAttribute="searchResult">
        <table class="table table-striped">
            <th><h4>Train</h4></th><th><h4>Buy ticket</h4></th>
            <c:forEach var="train" items="${searchResult}">
                <tr>
                    <td>${train.name}</td><td>Buy ticket</td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


