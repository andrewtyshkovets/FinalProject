<!DOCTYPE html>
<html>
<head>
    <title>Senior cashier</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading" align="center">
        Senior cashier
    </div>
    <div align="center">
    <form method="post" action="/cancelBill">
        <input type="number" name="bill_id" placeholder="Bill ID" class="input-field">
        <input type="submit" value="Cancel bill" for="bill_id">

    </form>
    <br>
    <form method="post" action="/makeX">
        <input type="number" name="cashBox_id" placeholder="CashBox ID" class="input-field">
        <input type="submit" value="Make X-report" for="cashBox_id">
    </form>
    <br>
    <form method="post" action="/makeZ">
        <input type="number" name="cashBox_id" placeholder="CashBox ID" class="input-field">
        <input type="submit" value="Make Z-report" for="cashBox_id">
    </form>
    <br>
    <form method="post" action="/logOut">
        <input type="submit" value="Log out">
    </form>
    </div>
</div>
</body>
</html>