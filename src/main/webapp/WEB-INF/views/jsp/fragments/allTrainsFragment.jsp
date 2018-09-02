<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">
    <h2>All trains list</h2>
	<table>
		<tr><th>ID</th><th>Name</th><th>Capacity</th><th>Delete train</th>

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


