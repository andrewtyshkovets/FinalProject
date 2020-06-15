<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of products</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-3">
    <div class="form-style-2-heading">
        List of products
    </div>
    <table class="products" border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Code</th>
            <th>Quantity</th>
            <th>Weight</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productCode}</td>
                <td>${product.productName}</td>
                <c:choose>
                    <c:when test="${product.quantity != -100500}">
                        <td>${product.quantity}</td>
                    </c:when>
                    <c:otherwise>
                        <td> - </td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${product.measure != -100500}">
                        <td>${product.measure}</td>
                    </c:when>
                    <c:otherwise>
                        <td> - </td>
                    </c:otherwise>
                </c:choose>
                <td>${product.pricePerMeasureOrQuantity}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <form method="post" action="/backToUser">
        <input type="submit" value="Back">
    </form>
</div>
</body>
</html>