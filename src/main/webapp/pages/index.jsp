<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
        <title>Advertisements</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
        <h1 style="background-color: #3F3F3F; font-family: 'Comic Sans MS'; color: white; padding: 10px; margin: 0px;" align="center">Advertisement list</h1>
        <div id="login-row" align="right" >
            <c:if test="${inBasketCount > 0}">
            <div <c:if test="${inBasketCount > 0}">style="background-color: #ADFF2F"</c:if>>
                <table>
                    <tr>
                        <td><label>You are logged as ADMIN</label></td>
                    </tr>
                </table>
            </div>
            </c:if>
            <c:if test="${inBasketCount < 1}">
            <div style="background-color: #DC143C; height: 35px">
                <table style="border-spacing: 10px 0px; border-collapse: separate">
                    <tr>
                        <td><p style="font-size: 20px">You are logged as
                            <span><b>Guest</b></span></p>
                        </td>
                        <td>
                            <form role="form" action="/login">
                                <input style="background-color: #FFC0CB" type="submit" class="btn btn-default" value="Sign in">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
            </c:if>
        </div>

        <form class="form-inline" role="form" action="/search" method="post">
            <input type="text" class="form-control" name="pattern" placeholder="Short desc">
            <input type="submit" class="btn btn-default" value="Search">
            <input type="hidden" name="location" value="">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form id="check" role="form" action="/administration/doAction" method="post">
        <table class="table table-striped">
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
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <!--User -->
        <div <c:if test="${inBasketCount < 1}"> style="background-color: #FFC0CB; height: 57px"</c:if>>
            <table align="center">
                <tr valign="top">
                    <td >
                        <form id="add_page" role="form" action="/auth/add_page" method="post">
                            <button style="height: 56px; width: 220px; background-color: inherit"
                                    type="submit" class="btn btn-default">
                                <img width="40" height="40" src="images/add.png">Add new Advertisement</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </td>
                    <td>
                        <button style="width: 220px; height: 56px; background-color: inherit" class="btn btn-default" form="check"
                                type="submit" formaction="/auth/export">
                            <img width="40" height="40" src="images/export.png">Export to XML</button>
                    </td>
                </tr>
            </table>
        </div>
        <!--Admin -->
        <div <c:if test="${inBasketCount <1 }"> style="background-color: #FFC0CB; height: 57px"</c:if>>
            <table align="center">
                <tr valign="top">
                    <td>
                        <button style="height: 56px; width: 220px; background-color: inherit" class="btn btn-default" form="check"
                                type="submit" name="action" value="remove"><img width="40" height="40" src="images/toBasket.png">
                            Remove to basket</button>
                    </td>
                    <td>
                        <form role="form" action="/administration/basket" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button style="height: 56px; width: 220px; background-color: inherit" type="submit "
                                    class="btn btn-default"><img width="40" height="40" src="images/basket.png">
                                Basket (<b>${inBasketCount}</b> advs)</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>