<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SonarQube Client</title>
        <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1><a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-lg"><spring:message code="label.page.review.title.back" text="Back" /></a> <spring:message code="label.page.review.title" text="Metrics" /> : ${projectKeyName}</h1>
            <h1>
                <span class="label ${result.finalResult.status.cssClass}"><spring:message code="${result.finalResult.status.label}" /></span>
            </h1>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h3><spring:message code="label.page.review.title.codeQuality" text="Code Quality" /></h3>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th><spring:message code="label.page.review.title.number" text="#" /></th>
                                    <th><spring:message code="label.page.review.title.measure" text="Measure" /></th>
                                    <th><spring:message code="label.page.review.title.value" text="Value" /></th>
                                    <th><spring:message code="label.page.review.title.result" text="Result" /></th>
                                </tr>
                            </thead>
                            <tbody
                                <tr>
                                    <td>1</td>
                                    <td><spring:message code="label.page.review.title.codelines" text="Lines of code" /></td>
                                    <td class="text-right"><fmt:formatNumber value="${result.codeQuality.codeLines.value}"  maxFractionDigits="0" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><spring:message code="label.page.review.title.comments" text="Comments" /></td>
                                    <td class="text-right"><fmt:formatNumber value="${result.codeQuality.comments.value}"  maxFractionDigits="0" /></td>
                                    <td>
                                        <span class="label ${result.codeQuality.comments.status.cssClass}"><spring:message code="${result.codeQuality.comments.status.label}" /></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><spring:message code="label.page.review.title.duplications" text="Duplications" /></td>
                                    <td class="text-right"><fmt:formatNumber value="${result.codeQuality.duplications.value}"  maxFractionDigits="0" /></td>
                                    <td>
                                        <span class="label ${result.codeQuality.duplications.status.cssClass}"><spring:message code="${result.codeQuality.duplications.status.label}" /></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <h3 class="text-center">
                            <span class="label ${result.codeQuality.finalResult.status.cssClass}"><spring:message code="${result.codeQuality.finalResult.status.label}" /></span>
                        </h3>
                    </div>
                </div>
                <div class="col-sm-4">
                    <h3><spring:message code="label.page.review.title.problems" text="Problems" /></h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th><spring:message code="label.page.review.title.number" text="#" /></th>
                                <th><spring:message code="label.page.review.title.measure" text="Measure" /></th>
                                <th><spring:message code="label.page.review.title.value" text="Value" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td><spring:message code="label.page.review.title.bug" text="Bug" /></td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.bug.value}"  maxFractionDigits="0" /></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td><spring:message code="label.page.review.title.blocker" text="Blocker" /></td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.blocker.value}"  maxFractionDigits="0" /></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td><spring:message code="label.page.review.title.critical" text="Critical" /></td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.critical.value}"  maxFractionDigits="0" /></td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td><spring:message code="label.page.review.title.major" text="Major" /></td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.major.value}"  maxFractionDigits="0" /></td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td><spring:message code="label.page.review.title.minor" text="Minor" /></td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.minor.value}"  maxFractionDigits="0" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <h3 class="text-center">
                        <span class="label ${result.bugs.finalResult.status.cssClass}"><spring:message code="${result.bugs.finalResult.status.label}" /></span>
                    </h3>
                </div>
                <div class="col-sm-4">
                    <h3><spring:message code="label.page.review.title.security" text="Security" /></h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th><spring:message code="label.page.review.title.number" text="#" /></th>
                                <th><spring:message code="label.page.review.title.measure" text="Measure" /></th>
                                <th><spring:message code="label.page.review.title.value" text="Value" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td><spring:message code="label.page.review.title.security" text="Security" /></td>
                                <td class="text-right"><fmt:formatNumber value="${result.security.security.value}"  maxFractionDigits="0" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <h3 class="text-center">
                        <span class="label ${result.security.finalResult.status.cssClass}"><spring:message code="${result.security.finalResult.status.label}" /></span>
                    </h3>
                </div>
            </div>
        </div>
        <script src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
