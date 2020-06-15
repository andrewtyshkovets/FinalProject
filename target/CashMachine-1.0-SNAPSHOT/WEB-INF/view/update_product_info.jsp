<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update product</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading" align="center">Update</div>
    <form method="post" action="/confirmUpdating">
        <table class="add_Product">
            <tr>
                <td><label>Code</label></td>
                <td>${productCode}</td>
            </tr>
            <tr>
                <td><label>Name</label></td>
                <td>${productName}</td>
            </tr>
            <tr>
                <td><label for="amount">Amount</label></td>
                <td> <input class="input-field" type="number" step="0.01" id="amount" name="amount"></td>
            </tr>
            <tr>
                <td><label for="price">Price</label></td>
                <td><input class="input-field" type="number" step="0.01"  id="price" name="price"></td>   
            </tr>        
        </table>
        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>