<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
    <link href="../../../css/styles.css" rel="stylesheet"/>

</head>
<body>
<h2>Products List</h2>


<hr>
<table id="main-container">
    <tr>
        <td>
            <form id="make_order" action="controller">
                <input type="hidden" name="command" value="makeOrder"/>
                <input type="submit" value='make_an_order'/>
                <table>
                    <tr>
                        <td>#</td>
                        <td>Checkboxes</td>
                        <th>Name</th>
                        <br>
                        <th>Price</th>
                        <br>
                        <th>Picture</th>
                        <br>
                        <th>Add to the Busket</th>
                    </tr>
                    <jsp:useBean id="listOfProducts" scope="session" type="java.util.List"/>
                    <c:set var="k" value="0"/>
                    <c:forEach var="product" items="${listOfProducts}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td><label>
                                <input type="checkbox" name="itemId" value="${product.id}"/>
                            </label></td>
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
                            <td>
                                    <%-- <c:if test="${sessionScope.user != null}"> --%>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="addToBasket">
                                    <div role="group">
                                        <input type="number" step="1" min="1"
                                               max="100"
                                               id="num_count"
                                               name="quantity" value="1" title="Qty">
                                        <button type="submit" name="productId" value="${product.id}">
                                            Add product to the Basket
                                        </button>
                                    </div>
                                </form>
                            </td>
                                <%--  </c:if>--%>

                        </tr>
                    </c:forEach>
                </table>
            </form>

            <form action="controller" method="post">
                <input type="hidden" name="command" value="sort">
                <input type="submit" name="sort" value="Sort products by color"/>
                <input type="submit" name="sort" value="Sort products by name"/>
                <input type="submit" name="sort" value="Sort products by price"/>
            </form>
        </td>
    </tr>

</table>
</body>
</html>