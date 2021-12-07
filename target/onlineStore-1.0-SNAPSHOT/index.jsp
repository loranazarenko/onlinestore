<%--
  Created by IntelliJ IDEA.
  User: LORA
  Date: 07.11.2021
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<h2>Products List</h2>

<table>
    <tr>
        <th>Name</th>
        <br>
        <th>Price</th>
        <br>
        <th>Picture</th>
    </tr>
    <jsp:useBean id="listOfProducts" scope="session" type="java.util.List"/>
    <c:forEach var="product" items="${listOfProducts}">
        <tr>
            <td>${product.name}</td>
            <br>
            <td>${product.price}</td>
            <br>
            <td>
                <div>
                    <img src="assets/<c:out value="${product.picture}"/>" width="150"
                         height="150" alt="picture">
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="controller" method="post">
    <input type="hidden" name="command" value="sort">
    <input type="submit" name="sort" value="Sort products by color"/>
    <input type="submit" name="sort" value="Sort products by name"/>
    <input type="submit" name="sort" value="Sort products by price"/>
</form>

<hr>

<form action="controller" method="post">
    <input type="hidden" name="command" value="login">
    <label>
        <input name="login">
    </label><br>
    <label>
        <input type="password" name="password">
    </label><br>
    <input type="submit" value="login">
</form>

<hr>

<form action="controller" method="post">
    <input type="hidden" name="command" value="logup">
    <button type="submit" value="logup">logup</button>
</form>


</body>
</html>