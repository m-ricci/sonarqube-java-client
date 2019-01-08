<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SonarQube Client</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/review" method="post">
            <input type="text" name="projectKey" >
            <input type="submit" value="Submit">
        </form>
    </body>
</html>