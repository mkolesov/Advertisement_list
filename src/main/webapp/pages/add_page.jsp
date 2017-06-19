<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
        <title>Add advertisement</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1 style="background-color: #3F3F3F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Advertisement list</h1>
        <h1 style="background-color: #ADFF2F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Add new advertisement</h1>
            <form id="new_adv" role="form" enctype="multipart/form-data" class="form-vertical" action="/auth/add" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table align="center"  <c:if test="${not empty error}">style="border: solid; border-color: #DC143C"</c:if>>
                    <c:if test="${not empty error}">
                        <tr align="center">
                            <td>
                                <div style="color: #DC143C; font-size: 140%">${error}</div>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <div style="width: 500px">
                                <input type="text" class="form-control" name="name" placeholder="Name">
                                <input type="text" class="form-control" name="shortDesc" placeholder="Short description">
                                <input type="text" class="form-control" name="longDesc" placeholder="Long description">
                                <input type="text" class="form-control" name="phone" placeholder="Phone">
                                <input type="text" class="form-control" name="price" placeholder="Price">
                            </div>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <div style="outline: 1px solid #ccc">
                                Photo: <input type="file" name="photo">
                            </div>

                        </td>
                        </tr>
                    <tr align="center">
                        <td>
                            <button form="new_adv" style="height: 56px; width: 240px; margin-top: 10px" type="submit "
                                    class="btn btn-default"><img width="40" height="40" src="images/add.png">
                                Add Advertisement</button>
                        </td>
                    </tr>
                </table>
             </form>
        <div align="center" style="margin-top: 50px">
            <form enctype="multipart/form-data" role="form" action="/auth/import" method="post">
                <button style="height: 56px; width: 290px;" type="submit" class="btn btn-default" align="center">
                     Import from XML<input type="file" name="import" hidden></button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <a href="/index" class="btn btn-default"><img width="40" height="40" src="images/back.png">
                Back to adv list</a>
        </div>
    </body>
</html>