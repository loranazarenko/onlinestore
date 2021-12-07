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
    <form action="controller" method="post">
        <input type="hidden" name="command" value="catalog">
        <button type="submit" value="catalog">Catalog of products</button>
    </form>
    <br>
    <br>
    <form id="adminUserOrders" method="post" action="controller">
        <input type="hidden" name="command" value="adminUserOrders"/>
        <input type="submit" value='All users orders'/>
    </form>
    <br>
    <br>

    <form id="adminUsers" method="post" action="controller">
        <input type="hidden" name="command" value="adminUsers"/>
        <input type="submit" value='All Users '/>
    </form>
    <br>
    <br>
    <a href="index.jsp"> Return to enter </a>
</div>

<%@ include file="/footer.jspf" %>

</body>
</html>
