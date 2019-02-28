<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="shortcut icon" type="image/png" href="/favicon.png">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
    crossorigin="anonymous">
    <link rel="stylesheet" href="styles.css">
    <title>Spring Boot</title>

</head>

<body>

   <div class="container">

      <div class="title">
        <h1>Countries</h1>
      </div>

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
    </div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted"><a href="https://danielme.com/spring/">danielme.com</a></p>
      </div>
    </footer>

</body>

</html>