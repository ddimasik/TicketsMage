<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<table>
		<tr><th>ID</th><th>Name</th><th>Capacity</th>

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


