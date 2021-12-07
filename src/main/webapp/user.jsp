<%@ page language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<html>
<head>
    <%@ include file="/header.jspf" %>
    <title>Hello, ${sessionScope.user.login}!!!</title>
</head>
<body>
<%@ include file="/headerbody.jspf" %>
<div style="margin-right: auto;margin-left: auto" class="text-center text-black">

    <c:set var="greeting" value="Hello"/>
    <c:set var="name" value="${sessionScope.user.login}"/>
    <h2>
        <h:response greeting="${greeting}"
                    name="${name}"/>
    </h2>
    <br>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="catalog">
        <button type="submit" value="catalog">Catalog of products</button>
    </form>
    <br>
    <br>

    <form class="flex" action="controller" method="get">
        <input type="hidden"  name="command" value="pageCart">
        <button class="btn btn-outline-dark" type="submit">
            <i class="bi-cart-fill me-1"></i>
            User Cart
        </button>
    </form>
    <br>
    <br>

    <form id="userOrders" method="get" action="controller">
        <input type="hidden" name="command" value="userOrders"/>
        <input type="submit" value='User Orders'/>
    </form>
    <br>
    <br>
</div>
<a href="index.jsp"> Return to enter </a>

<footer>
    <%@ include file="/footer.jspf" %>
</footer>
</body>
</html>
