<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LoginPage</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Style.css"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign In!
    </div>
    <form method="post" action="/login">
        <table>
            <tr>
                <td>
                    <label for="username">User name</label>
                </td>
                <td>
                    <input class="input-field" type="text" id="username" name="username">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Password </label>
                </td>
                <td>
                    <input class="input-field" type="password" id="password" name="password">
                </td>
        </table>
             <input class="input_button" type="submit" value="Sign In">

    </form>

    <form class="registration_button" method="get" action="/registration"><input class="reg_button" type="submit" value="Sign Up"></form>

</div>
</body>
</html>