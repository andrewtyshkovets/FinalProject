<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cashier</title>
    <link href="${pageContext.request.contextPath}/CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Cashier
    </div>
    <br>
    <form method="post" action="/openCashBox">

        <input type="number" name="number_of_cashbox" id="number_of_cashbox" placeholder="Cashbox №"
               class="input-field-2">
        <input type="number" step="0.01" name="start_money" id="start_money" placeholder="Start money"
               class="input-field-2">
        <script type="text/javascript">
            window.onload = function () {
                document.getElementById("number_of_cashbox").onchange = validate;
                document.getElementById("start_money").onchange = validate;
            }

            function validate() {
                var space = "";
                var var1 = document.getElementById("number_of_cashbox").value;
                var var2 = document.getElementById("start_money").value;
                if (var1 == space || var2 == space)
                    document.getElementById("number_of_cashbox").setCustomValidity("elements has to be not null or wrong number");
                else
                    document.getElementById("number_of_cashbox").setCustomValidity('');
            }
        </script>
        <input type="submit" value="Open cashbox" for="number_of_cashbox">
    </form>
    <form method="post" action="/logOut">
        <input type="submit" value="Log out">
    </form>
</div>
</body>
</html>