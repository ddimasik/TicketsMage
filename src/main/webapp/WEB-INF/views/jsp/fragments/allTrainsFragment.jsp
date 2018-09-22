<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<div class="container">

    <h2>All trains list
        <c:if test="${not empty scheduleSwitcher}">
            passing station ${scheduleSwitcher}
        </c:if>
    </h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">Clear</span>
            </button>
            <strong>${msg} ${trainId} ${trainName}</strong>
        </div>
    </c:if>

    <table class="table table-striped" >
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Capacity</th>
            <th>Start date & time</th>
            <th>Station</th>
            <th>Time</th>
            <security:authorize ifAnyGranted="ROLE_ADMIN">
                <th>Passengers</th>
            </security:authorize>
            <%--<th>Delete train</th>--%>
        </tr>

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
                        ${train.startDateTime}
                </td>
                <td>
                        <c:forEach var="stationId" items="${train.stationName}">
                            <span>${stationId}</span>
                            <c:if test="${stationId == scheduleSwitcher}">
                                <span class="badge badge-light">This</span>
                            </c:if>
                            <br>
                        </c:forEach>
                </td>
                <td>
                        <c:forEach var="minutesFromStartStn" items="${train.timeOnStation}">
                            <span>${minutesFromStartStn}</span>
                            <br>
                        </c:forEach>
                </td>
                <td>
                    <security:authorize ifAnyGranted="ROLE_ADMIN">
                        <form action="/trains/${train.id}/passengers" method="get">
                            <button type='submit' name='showPassengers'>Passengers</button>
                        </form>
                    </security:authorize>
                </td>
                <%--<td>
                    <form action="/trains/${train.id}/delete" method="post">
                        <button type='submit' name='delete' value='Delete'>Delete</button>
                    </form>
                </td>--%>
			</tr>
		</c:forEach>

	</table>
</div>

<jsp:include page="../fragments/footer.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


