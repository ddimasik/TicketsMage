<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>All trains list</h2>
	<table border="2" width="100%">
        <tr><th>ID</th><th>Name</th><th>Capacity</th><th>Delete train</th></tr>

		<c:forEach var="train" items="${trains}">
			<tr>
				<td>
						${train.id}
				</td>
				<td>
						${train.name}
				</td>
				<td>
						${train.capacity}
				</td>
				<td>
					<form action="/trains/${train.id}/delete" method="post">
						<button type='submit' name='delete' value='Delete'>Delete</button>
					</form>
				</td>
                <div>
                    <c:forEach var="station_id" items="${train.station_id}">
                    <tr>StationID: ${station_id} </tr>
                    </c:forEach>
                    <br>
                    <c:forEach var="time_on_station" items="${train.time_on_station}">
                    <tr>Time: ${time_on_station} </tr>
                    </c:forEach>
                    <br>
                </div>
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


