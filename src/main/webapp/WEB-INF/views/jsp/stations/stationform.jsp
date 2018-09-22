<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create station page</title>
</head>

<jsp:include page="../fragments/header.jsp" />

<body>

<div class="container">
    <h1>Add station</h1>
    <br />

    <spring:url value="/stations" var="stationActionUrl" />

    <form:form class="form-horizontal" method="post"
               modelAttribute="stationForm" action="${stationActionUrl}">

    <form:hidden path="id" />
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control"
                                id="name" placeholder="Name" />
                    <form:errors path="name" class="control-label" />
                </div>
            </div>
        </spring:bind>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn-lg btn-primary pull-right">Create station</button>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>