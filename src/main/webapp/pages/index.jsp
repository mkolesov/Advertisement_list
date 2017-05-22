<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>MyTestNew</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
        <h1 style="background-color: #3F3F3F; color: white; padding: 10px; margin: 0px;" align="center">Advertisement list</h1>
        <div id="login" align="right" <c:if test="${inBasketCount < 1}">style="background-color: #ADFF2F"</c:if>
        <c:if test="${inBasketCount > 0}">style="background-color: #DC143C"</c:if>>
            <table>
            <tr>
                <td><label>You are logged as Guest</label></td>
            </tr>
            </table>
        </div>

        <form class="form-inline" role="form" action="/search" method="post">
            <input type="text" class="form-control" name="pattern" placeholder="Short desc">
            <input type="text" hidden name="location" value="">
            <input type="submit" class="btn btn-default" value="Search">
        </form>
        <form id="check" role="form" action="/doAction" method="post">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>Photo</b></td>
                <td><b>Name</b></td>
                <td><b>Short Desc</b></td>
                <td><b>Long Desc</b></td>
                <td><b>Phone</b></td>
                <td><b>Price</b></td>
                <td><b>Select</b></td>
            </tr>
            </thead>
            <c:forEach items="${advs}" var="adv">
                <tr>
                    <td><img height="40" width="40" src="/image/${adv.photo.id}"/></td>
                    <td>${adv.name}</td>
                    <td>${adv.shortDesc}</td>
                    <td>${adv.longDesc}</td>
                    <td>${adv.phone}</td>
                    <td>${adv.price}</td>
                    <td><input type="checkbox" name="id" value="${adv.id}">

                    </td>
                </tr>
            </c:forEach>
        </table>
        <table>
            <tr valign="top">
                <td>
                    <form role="form" action="/add_page" method="post">
                        <button type="submit" class="btn btn-default">Add new adv</button>
                    </form>
                </td>
                <td>
                    <button class="btn btn-default" form="check" type="submit" name="action" value="remove">Remove to basket</button>
                </td>
                <td>
                    <form role="form" action="/basket">
                        <button type="submit" class="btn btn-default">Basket (<b>${inBasketCount}</b> advs)</button>
                    </form>
                </td>
            </tr>
        </table>
            <table>
            <tr valign="top">
                <td>
                    <form enctype="multipart/form-data" role="form" action="/import" method="post">
                        <button type="submit" class="btn btn-default">Import <input type="file" name="import"></button>
                    </form>
                </td>
                <td>
                    <button style="width: 75px; height: 56px" class="btn btn-default" form="check" type="submit"
                    name="action" value="export">Export</button>
                </td>
            </tr>
        </table>
        </form>



        <!--xml import/export-->

    </body>
</html>