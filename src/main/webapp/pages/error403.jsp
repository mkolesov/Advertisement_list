<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
        <title>Forbidden</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <style type="text/css">
            div.main {
                margin: 50px auto;
                padding: 0 0 0 0;
                width: 600px;
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
            <h1 style="background-color: #3F3F3F; color: white; padding: 10px; margin: 0px; font-size: 300%" align="center">ACCESS DENIED</h1>
            <div style="background: white; border: black; padding: 10px; margin: 0px;" align="center" dir="ltr">
               <h1>You don't have permission for that</h1>
                <h3>or your session was closed</h3>
            <a style="margin-top: 5px; width: 140px; height: 33px"  href="/index" class="btn btn-default">Back to adv list</a>
                OR
            <a style="width: 140px; height: 33px" href="/login" class="btn btn-default">Log in</a>
        </div>
    </body>
</html>