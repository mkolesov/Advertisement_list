<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
        <title>Basket</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
        <h1 style="background-color: #3F3F3F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Advertisement list</h1>
        <h1 style="background-color: #00FFFF; font-family: 'Comic Sans MS'; color: black; padding: 10px; margin: 0px;" align="center">Basket</h1>
        <form class="form-inline" role="form" action="/search" method="post">
            <input type="text" class="form-control" name="pattern" placeholder="Short desc">
            <input type="submit" class="btn btn-default" value="Search">
            <c:if test="${not empty inSearch}">
                <a href="/administration/basket" class="btn btn-default">Cancel filtering</a>
            </c:if>
            <input type="hidden"  name="location" value="basket">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <form id="check" role="form" action="/administration/doAction" method="post">
            <div style="max-height: 70%;overflow: auto">
                <table class="table table-striped" style="table-layout: fixed;">
                    <col width="10%">
                    <col width="15%">
                    <col width="15%">
                    <col width="25%">
                    <col width="15%">
                    <col width="10%">
                    <col width="10%">
                    <thead>
                    <tr align="center">
                        <td><b>Photo</b></td>
                        <td><b>Name</b></td>
                        <td><b>Short Desc</b></td>
                        <td><b>Long Desc</b></td>
                        <td><b>Phone</b></td>
                        <td><b>Price</b></td>
                        <td ><b>Select</b></td>
                    </tr>
                    </thead>
                    <c:forEach items="${advs}" var="adv">
                        <tr align="center">
                            <td><img height="60" width="60" src="/image/${adv.photo.id}"/></td>
                            <td style="overflow: auto">${adv.name}</td>
                            <td style="overflow: auto">${adv.shortDesc}</td>
                            <td style="overflow: auto">${adv.longDesc}</td>
                            <td style="overflow: auto">${adv.phone}</td>
                            <td>${adv.price}</td>
                            <td><input type="checkbox" name="id" value="${adv.id}"></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <c:if test="${not empty inSearch and empty advs}">
                <h5 align="center" style="font-size:170%">No such result</h5>
            </c:if>
            <c:if test="${empty advs and empty inSearch}">
                <h5 align="center" style="font-size:170%">No advertisements here</h5>
            </c:if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <table align="center">
            <tr>
                <td>
                    <form role="form" method="post">
                        <a href="/index" class="btn btn-default"><img width="40" height="40" src="images/back.png">
                            Back to adv list</a>
                        <button class="btn btn-default" form="check" type="submit" name="action" value="restore">
                        <img width="40" height="40" src="images/restore.png">Restore selected</button>
                        <button type="submit" formaction="/administration/restore_all" class="btn btn-default"><img width="40" height="40" src="images/restoreAll.png">Restore all</button>
                        <button formaction="/administration/clean_basket" type="submit" class="btn btn-default"><img width="40" height="40" src="images/clear.png">
                            Clear basket</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>