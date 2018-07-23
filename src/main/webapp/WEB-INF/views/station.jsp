<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>"Stations list"</title>
</head>
<body>

<%--<a href="<c:url value="/logout" />">--%>
    <%--<spring:message code="label.logout" />--%>
<%--</a>--%>

<h2>Stations list</h2>

<form:form method="post" action="add" commandName="station">

    <table>
        <tr>
            <td><form:label path="name">Station name input window</form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add Station" /></td>
        </tr>
    </table>
</form:form>

<%--<h3><spring:message code="label.contacts" /></h3>--%>
<c:if test="${!empty stationsList}">
    <table class="data">
        <tr>
            <th>Station name</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${stationsList}" var="st">
            <tr>
                <td>${st.name}</td>
                <td><a href="edit/${st.id}">Edit</a></td>
                <td><a href="delete/${st.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>