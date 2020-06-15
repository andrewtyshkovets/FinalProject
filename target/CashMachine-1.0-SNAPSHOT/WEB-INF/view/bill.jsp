<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bill</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-3">
    <table class="add_Product" border="1" align="center">
        <tr>
            <td>№ bill</td>
            <td>${billNumber}</td>
        </tr>
        <tr>
            <td>№ CashBox</td>
            <td>${cashBoxNumber}</td>
        </tr>
        <tr>
            <td>Cashier</td>
            <td>${cashier}</td>
        </tr>
        <tr>
            <td>Date</td>
            <td>${date}</td>
        </tr>
    </table>
    <br>
    <table border="1" align="center" class="add_Product">
        <tr>
            <td>
                <table border="0" align="center" class="add_Product">
                    <tr>
                        <th>Product ID</th>
                        <th>Amount</th>
                        <c:forEach items="${productAmount}" var="product">
                    <tr>
                        <td>${product.key}</td>
                        <td>${product.value}</td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <table border="0" align="center" class="add_Product">
                    <th>Price</th>
                    <c:forEach items="${productPrice}" var="price">
                        <tr>
                            <td>
                                    ${price.value}
                            </td>
                        </tr>

                    </c:forEach>
                </table>
            </td>
        </tr>
        <tfoot>
        <td colspan="3">Total</td>
        <td>${total}</td>
        </tfoot>
    </table>
    <br>
    <table align="center">
        <tr>
            <td>
                <form method="post" action="/addProductByCode"><input type="submit" value="Add by code"></form>
            </td>
            <td>
                <form method="post" action="/addProductByName"><input type="submit" value="Add by name"></form>
            </td>
        </tr>
    </table>
    <br>
    <form method="post" action="/closeBill" align="center">
        <input type="submit" name="close" value="Close">
    </form>
    <form method="post" action="/backToUser" align="center">
        <input type="submit" name="backToUser" value="Back">
    </form>
</div>
</body>
</html>