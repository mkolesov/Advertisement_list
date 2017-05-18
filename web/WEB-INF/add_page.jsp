<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Contact</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add" method="post">
            <div class="form-group"><h3>New Adv</h3></div>
            <div class="form-group"><input class="form-control" type="text" name="name" placeholder="Name"></div>
            <div class="form-group"><input class="form-control" type="text" name="shortDesc" placeholder="Short description"></div>
            <div class="form-group"><input class="form-control" type="text" name="longDesc" placeholder="Long description"></div>
            <div class="form-group"><input class="form-control" type="text" name="phone" placeholder="Phone"></div>
            <div class="form-group"><input class="form-control" type="text" name="price" placeholder="Price"></div>
            <div class="form-group">Photo: <input type="file" name="photo"></div>
            <div class="form-group"><input class="btn btn-primary" type="submit" value="Add"></div>
        </form>
    </div>
    </body>
</html>