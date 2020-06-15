<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Report</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading" align="center">Report</div>
    <form>
        <table class="add_Product" align="center" border="1">
            <tr>
                <th>Report ID</th>
                <td>${reportId}</td>
            </tr>
            <tr>
                <th>Type</th>
                <td>${type}</td>
            </tr>
            <tr>
                <th>Cashbox ID</th>
                <td>${cashBoxId}</td>
            </tr>
            <tr>
                <th>Cashier ID</th>
                <td>${cashier}</td>
            </tr>
             <tr>
                <th>Start time</th>
                <td>${startTime}</td>
            </tr>
            <tr>
                <th>Start money</th>
                <td>${startMoney}</td>
            </tr>
            <tr>
                <th>Current time</th>
                <td>${currentTime}</td>
            </tr>
            <tr>
                <th>Current money</th>
                <td>${currentMoney}</td>
            </tr>
            <tr>
                <th>Total sales</th>
                <td>${totalSales}</td>
            </tr>        
        </table> 
        <br>
    </form>
    <form align="center" method="post" action="/backToUser">
        <input type="submit" value="OK">
    </form>
</div>
</body>
</html>