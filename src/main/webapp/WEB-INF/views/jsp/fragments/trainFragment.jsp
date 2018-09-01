<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <h2>Add new train</h2>
        <form:form method="POST" action="/allTrains" modelAttribute="trainFragment">
        <table>
            <tr>
                <td><spring:message text="Name"/></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><spring:message text="Capacity"/></td>
                <td><form:input path="capacity"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"></td>
            </tr>

        </table>
    </form:form>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>

