<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="form-style-2">
    <form method="post" action="/registration">
        <table>
            <tr>
                <td><label for="username">User name </label></td>
                <td align="right"><input class="input-field" type="text" id="username" name="username"></td>
            </tr>
            <tr>
                <td><label for="password">Password </label></td>
                <td><input class="input-field" type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <td><label for="password_confirm">Confirm password </label></td>
                <td><input class="input-field" type="password" id="password_confirm" name="password_confirm"></td>
            </tr>
            <tr>
                <td><label for="full-name">Full name </label></td>
                <td><input class="input-field" type="text" id="full-name" name="full-name"></td>
        </table>
        <script type="text/javascript">
            window.onload = function () {
                document.getElementById("password").onchange = validatePassword;
                document.getElementById("password_confirm").onchange = validatePassword;
            }
            function validatePassword(){
                var pass2=document.getElementById("password_confirm").value;
                var pass1=document.getElementById("password").value;
                if(pass1!=pass2)
                    document.getElementById("password_confirm").setCustomValidity("Passwords Doesn't Match");
                else
                    document.getElementById("password_confirm").setCustomValidity('');
            }
        </script>
        <br/>
        <div class="choose_role">Choose a role:
            <select name="role">
                <option value="CASHIER">cashier</option>
                <option value="SENIOR_CASHIER">senior cashier</option>
                <option value="PRODUCT_OBSERVER">product observer</option>
            </select>
        </div><br/>
        <input type="submit" value="Register">
    </form>
</div>
</body>
</html>