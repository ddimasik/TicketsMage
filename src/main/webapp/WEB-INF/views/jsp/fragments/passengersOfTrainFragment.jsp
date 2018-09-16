<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Passengers of train ${trainId} ${trainName}</h2>
	<table class="table table-striped" width="80%" >
        <tr><th>Name</th><th>Surname</th><th>Birthday</th>

		<c:forEach var="passenger" items="${passengers}">

            <tr>
				<td>
						${passenger.name}
				</td>
				<td>
						${passenger.surname}
				</td>
				<td>
						${passenger.birthday}
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


