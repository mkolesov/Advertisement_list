<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <title>Форма Авторизации</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style type="text/css">
        div.main {
            margin: 50px auto;
            padding: 0 0 0 0;
            width: 340px;
            border-color: #3F3F3F;
            border-style: solid;
            border-radius: 5px;
            overflow: hidden;
        }
    </style>
</head>
<body>
<h1 style="background-color: #3F3F3F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Advertisement list</h1>
<div class="main">

    <h1
            style="background-color: #3F3F3F; color: white; padding: 10px; margin: 0px;" align="center">Authorisation </h1>
    <div
            style="background: white; border: black; padding: 10px; margin: 0px;"
            align="center" dir="ltr">
        <c:if test="${param.containsKey('error')}">
            <div style="color: #DC143C">Invalid login or password, try again.</div>
        </c:if>
        <form name="f" action="/j_spring_security_check"
              method="POST" style="background: white;">
            <table width="274px">
                <tr>
                    <td>Login: </td>
                    <td><input type='text' name='j_username'/>
                    </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type='password' name='j_password'>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan='2' align="center">
                            <input style="margin-top: 5px" class="btn btn-default" name="submit" value="Submit" type="submit">
                            <input style="margin-top: 5px" name="reset" class="btn btn-default" value="Reset" type="reset">
                    </td>
                </tr>
                <tr align="center">
                    <td colspan='2' align="center">
                        <a style="margin-top: 5px"  href="/index" class="btn btn-default"><img width="25" height="25" src="images/back.png">
                            Back to adv list</a>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</div>
</body>
</html>