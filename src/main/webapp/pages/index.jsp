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

        <!--Guest -->
        <c:if test="${role eq 'ROLE_ANONYMOUS'}">
            <div style="background-color: #DC143C; height: 34px" align="right">
                <table style="border-spacing: 10px 0px; border-collapse: separate">
                    <tr>
                        <td>
                            <div style="font-size: 20px;">You are logged as
                            <span><b>Guest</b></span></div>
                        </td>
                        <td>
                            <a style="background-color: #FFC0CB" href="/login" class="btn btn-default">Log in</a>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>

        <!--User -->
        <c:if test="${role eq 'ROLE_USER'}">
            <div style="background-color: #ADFF2F; height: 34px" align="right">
                <table style="border-spacing: 10px 0px; border-collapse: separate">
                    <tr>
                        <td valign="top">
                            <div style="font-size: 20px; margin-top: 6px">You are logged as
                                <span><b>${userName}</b></span>
                            </div>
                        </td>
                        <td>
                            <form role="form" action="/logout" method="post">
                                <input style="background-color: #98FB98" type="submit" class="btn btn-default" value="Log out">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>

        <%--Admin--%>
        <c:if test="${role eq 'ROLE_ADMIN'}">
            <div style="background-color: #00FFFF; height: 34px" align="right">
                <table style="border-spacing: 10px 0px; border-collapse: separate">
                    <tr>
                        <td valign="top">
                            <div style="font-size: 20px; margin-top: 6px">You are logged as
                                <span><b>${userName}</b></span>
                            </div>
                        </td>
                        <td>
                            <form role="form" action="/logout" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input style="background-color: #40E0D0;" type="submit" class="btn btn-default" value="Log out">
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
            <c:if test="${not empty inSearch}">
                <a href="/index" class="btn btn-default">Cancel filtering</a>
            </c:if>
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
                    <td><input type="checkbox" name="id" value="${adv.id}"></td>
                </tr>
            </c:forEach>
        </table>
            <c:if test="${not empty inSearch and empty advs}">
                <h5 align="center" style="font-size:170%">No such result</h5>
            </c:if>
            <c:if test="${empty advs and empty inSearch}">
                <h5 align="center" style="font-size:170%">No advertisements here</h5>
            </c:if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <%-- LOG IN TO GET FEATURES--%>
        <c:if test="${role eq 'ROLE_ANONYMOUS'}">
            <div style="height: 56px" align="center">
                <div style="font-size:170%">
                    <b>Log in to unlock advanced actions</b>
                </div>
                <a style="height: 56px; width: 220px;" href="/login" class="btn btn-default"><div style="margin-top: 12px">Log in</div></a>
            </div>
        </c:if>

        <!--User -->
        <c:if test="${role eq 'ROLE_USER' or role eq 'ROLE_ADMIN'}">
        <div style="background-color: #ADFF2F; height: 56px">
            <table align="center">
                <tr valign="top">
                    <td >
                        <form id="add_page" role="form" action="/auth/add_page" method="post">
                            <button style="height: 56px; width: 220px; background-color: #98FB98"
                                    type="submit" class="btn btn-default">
                                <img width="40" height="40" src="images/add.png">Add new Advertisement</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </td>
                    <td>
                        <button style="width: 220px; height: 56px; background-color: #98FB98" class="btn btn-default" form="check"
                                type="submit" formaction="/auth/export">
                            <img width="40" height="40" src="images/export.png">Export to XML</button>
                    </td>
                </tr>
            </table>
        </div>
        </c:if>

        <!--Admin -->
        <c:if test="${role eq 'ROLE_ADMIN' }">
        <div style="background-color: #00FFFF; height: 56px">
            <table align="center">
                <tr valign="top">
                    <td>
                        <button style="height: 56px; width: 220px; background-color: #40E0D0" class="btn btn-default" form="check"
                                type="submit" name="action" value="remove"><img width="40" height="40" src="images/toBasket.png">
                            Remove to basket</button>
                    </td>
                    <td>
                        <form role="form" action="/administration/basket" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button style="height: 56px; width: 220px; background-color: #40E0D0" type="submit "
                                    class="btn btn-default"><img width="40" height="40" src="images/basket.png">
                                Basket (<b>${inBasketCount}</b> advs)</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        </c:if>
    </body>
</html>