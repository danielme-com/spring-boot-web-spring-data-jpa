<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/png" href="/favicon.png">
<title>Spring Boot</title>
</head>
<body>
    <h1>Countries</h1>
    <hr>

    <c:choose>
        <c:when test="${not empty countriesList}">

            <ul>
                <c:forEach var="item" items="${countriesList}">
                    <li>${item.name}:<fmt:formatNumber
                            value="${item.population}" /></li>
                </c:forEach>
            </ul>

        </c:when>
        <c:otherwise>
            <b>NO DATA</b>
        </c:otherwise>
    </c:choose>


</body>
</html>