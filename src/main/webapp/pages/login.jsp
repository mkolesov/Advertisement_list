<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<html>
<head>
    <title>Форма Авторизации</title>
    <style type="text/css">
        body {
            background: #63bad8 50% 0px repeat-x;
            text-align: center;
        }

        div.main {
            margin: 50px auto;
            padding: 0 0 0 0;
            width: 340px;
            border-color: black;
        }
    </style>
</head>
<body>
<div class="main">
    <h1
            style="background-color: #3F3F3F; color: white; padding: 10px; margin: 0px;">Авторизация</h1>
    <div
            style="background: white; border: black; padding: 10px; margin: 0px;"
            align="center" dir="ltr">
        <c:if test="${not empty param.login_error}">
            <font color="red"> Не правильный логин или пароль. Попробуйте
                заново.</font>
        </c:if>
        <form name="f" action="<c:url value='basket.jsp'/>"
              method="POST" style="background: white;">
            <table>
                <tr>
                    <td style="font-style: oblique">Пользователь:</td>
                    <td><input type='text' name='j_username'
                               value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
                    </td>
                </tr>
                <tr>
                    <td style="font-style: oblique">Пароль:</td>
                    <td><input type='password' name='j_password'>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan='2' align="center"><input name="submit"
                                                          value="Войти" type="submit"> <input name="reset"
                                                                                              value="Очистить" type="reset">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>