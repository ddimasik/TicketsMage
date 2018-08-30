<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<table>
		<tr><th>Train</th><th>Passenger</th><th>Start station</th><th>End station</th><th>Date time</th></tr>

		<c:forEach var="ticket" items="${tickets}">
			<tr>
				<td>
						${ticket.train_id}
				</td>
				<td>
						${ticket.passenger_id}
				</td>
				<td>
						${ticket.startStation_id}
				</td>
				<td>
						${ticket.endStation_id}
				</td>
				<td>
						${ticket.dateTime}
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


