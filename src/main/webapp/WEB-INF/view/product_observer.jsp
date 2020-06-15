<!DOCTYPE html>
<html>
<head>
    <title>Product observer</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Product observer
    </div>
    <form method="post" action="/products">
        <input type="submit" value="Show all products">
    </form>
    <br>
    <form method="post" action="/addNewProduct">
        <input type="submit" value="Add new product">
    </form>
    <br>
    <form method="post" action="/updateProductInfo">
        <input type="submit" value="Update" for="product_id">
        <input type="number" name="product_id" placeholder="Product ID" class="input-field">
    </form>
    <br>
    <form method="post" action="/logOut">
        <input type="submit" value="Log out">
    </form>
</div>
</body>
</html>