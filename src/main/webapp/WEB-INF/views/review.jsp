<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Metryki</h1>
            <h1>
                <span class="label ${result.finalResult.status.cssClass}">${result.finalResult.value}</span>
            </h1>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h3>Jakość kodu</h3>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Metryka</th>
                                    <th>Wynik</th>
                                    <th>Ocena</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Komentarze</td>
                                    <td class="text-right"><fmt:formatNumber value="${result.codeQuality.comments.value}"  maxFractionDigits="0" /></td>
                                    <td>
                                        <span class="label ${result.codeQuality.comments.status.cssClass}"><spring:message code="${result.codeQuality.comments.status.label}" /></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Duplikaty</td>
                                    <td class="text-right"><fmt:formatNumber value="${result.codeQuality.duplications.value}"  maxFractionDigits="0" /></td>
                                    <td>
                                        <span class="label ${result.codeQuality.duplications.status.cssClass}"><spring:message code="${result.codeQuality.duplications.status.label}" /></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <h3 class="text-center">
                            <span class="label ${result.codeQuality.finalResult.status.cssClass}">${result.codeQuality.finalResult.value}</span>
                        </h3>
                    </div>
                </div>
                <div class="col-sm-4">
                    <h3>Błędy</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Metryka</th>
                                <th>Wynik</th>
                                <th>Ocena</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Bug</td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.bug.value}"  maxFractionDigits="0" /></td>
                                <td>
                                    <span class="label ${result.bugs.bug.status.cssClass}"><spring:message code="${result.bugs.bug.status.label}" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Blocker</td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.blocker.value}"  maxFractionDigits="0" /></td>
                                <td>
                                    <span class="label ${result.bugs.blocker.status.cssClass}"><spring:message code="${result.bugs.blocker.status.label}" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>Critical</td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.critical.value}"  maxFractionDigits="0" /></td>
                                <td>
                                    <span class="label ${result.bugs.critical.status.cssClass}"><spring:message code="${result.bugs.critical.status.label}" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>Major</td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.major.value}"  maxFractionDigits="0" /></td>
                                <td>
                                    <span class="label ${result.bugs.major.status.cssClass}"><spring:message code="${result.bugs.major.status.label}" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td>Minor</td>
                                <td class="text-right"><fmt:formatNumber value="${result.bugs.minor.value}"  maxFractionDigits="0" /></td>
                                <td>
                                    <span class="label ${result.bugs.minor.status.cssClass}"><spring:message code="${result.bugs.minor.status.label}" /></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <h3 class="text-center">
                        <span class="label ${result.bugs.finalResult.status.cssClass}">${result.bugs.finalResult.value}</span>
                    </h3>
                </div>
                <div class="col-sm-4">
                    <h3>Bezpieczeństwo</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Metryka</th>
                                <th>Wynik</th>
                                <th>Ocena</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Bezpieczeństwo</td>
                                <td class="text-right"><fmt:formatNumber value="${result.security.security.value}"  maxFractionDigits="0" /></td>
                                <td>
                                    <span class="label ${result.security.security.status.cssClass}"><spring:message code="${result.security.security.status.label}" /></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <h3 class="text-center">
                        <span class="label ${result.security.finalResult.status.cssClass}">${result.security.finalResult.value}</span>
                    </h3>
                </div>
            </div>
        </div>
        <script src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
