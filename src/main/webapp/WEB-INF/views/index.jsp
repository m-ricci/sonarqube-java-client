<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SonarQube Client</title>
        <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="jumbotron text-center">
            <c:if test = "${not empty error}">
                <div class="alert alert-danger">
                    <strong><spring:message code="${error}" text="${error}" /></strong>
                </div>
            </c:if>
            <h1><spring:message code="label.page.index.title" text="SonarQube client" /></h1>
            <form class="form-inline" action="${pageContext.request.contextPath}/review" method="post">
                <spring:message code="label.page.index.projectKey.placeholder" var="projectKeyPlaceholder" text="product key" />
                <input class="form-group form-control" type="text" name="projectKey" placeholder="${projectKeyPlaceholder}" >
                <spring:message code="label.page.index.submit" var="submitName" text="Submit" />
                <input class="btn btn-primary " type="submit" value="${submitName}">
            </form>
        </div>
    </body>
</html>