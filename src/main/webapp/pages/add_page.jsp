<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
        <title>New Contact</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <h1 style="background-color: #3F3F3F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Advertisement list</h1>
    <h1 style="background-color: #ADFF2F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Add new advertisement</h1>
    <div class="container">
        <form id="new_adv" role="form" enctype="multipart/form-data" class="form-horizontal" action="/auth/add" method="post">
            <div class="form-group"><input class="form-control" type="text" name="name" placeholder="Name"></div>
            <div class="form-group"><input class="form-control" type="text" name="shortDesc" placeholder="Short description"></div>
            <div class="form-group"><input class="form-control" type="text" name="longDesc" placeholder="Long description"></div>
            <div class="form-group"><input class="form-control" type="text" name="phone" placeholder="Phone"></div>
            <div class="form-group"><input class="form-control" type="text" name="price" placeholder="Price"></div>
            <div class="form-group"><table>
                <tr>
                    <td>
                        Photo: <input type="file" name="photo">

                    </td>
                    <td>
                        <input class="btn btn-primary" type="submit" value="Add Advertisement">
                    </td>
                </tr>
            </table></div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div align="center" style="margin-top: 100px">
        <form enctype="multipart/form-data" role="form" action="/auth/import" method="post">
            <button style="height: 56px; width: 290px;" type="submit" class="btn btn-default" align="center">
                Import from XML<input type="file" name="import" hidden></button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
            <form role="form" action="/index" method="post">
                <button type="submit" class="btn btn-default"><img width="25" height="25" src="images/back.png">
                    Back to adv list</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
    </body>
</html>