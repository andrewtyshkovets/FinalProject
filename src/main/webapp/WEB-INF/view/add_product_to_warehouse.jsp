<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New product</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="form-style-2">
    <h3 align="center">Quantity or Measure has to be zero</h3>
    <form method="post" action="/confirmAddingProduct">
        <table class="add_Product">
            <tr>
                <td><label for="code">Code</label></td>
                <td><input class="input-field" type="number" id="code" name="code"></td>
            </tr>
            <tr>
                <td><label for="name">Name</label></td>
                <td><input class="input-field" type="text" id="name" name="name"></td>
            </tr>
            <tr>
                <td><label for="quantity">Quantity</label></td>
                <td><input class="input-field" type="number" id="quantity" name="quantity" value="0"></td>
            </tr>
            <tr>
                <td><label for="measure">Measure</label></td>
                <td> <input class="input-field" type="number" step="0.0001" id="measure" name="measure" value="0"></td>
            </tr>
            <tr>
                <td><label for="price">Price</label></td>
                <td><input class="input-field" type="number" step="0.01"  id="price" name="price"></td>   
            </tr>        
        </table>
        <input type="submit" value="Add product">
    </form>
</div>
</body>
</html>