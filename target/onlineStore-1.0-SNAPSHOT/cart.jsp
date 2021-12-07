<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cart</title>
    <%@ include file="/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleCart.css" media="screen" title="no title">
    <style type="text/css">
        .table tbody td {
            vertical-align: middle;
        }

        .btn-incre, .btn-decre {
            box-shadow: none;
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@ include file="/headerbody.jspf" %>
<main>
    <form id="make_order" method="post" action="controller">
        <c:if test="${sessionScope.user != null}">
            <input type="hidden" name="command" value="makeOrder"/>
            <input type="submit" value='Make An Order'/>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <div class="alert alert-danger fade show" role="alert">
                No Rights
            </div>
        </c:if>
        <div class="shopping-cart">
            <div class="title">
                Shopping Cart
            </div>
            <%-- CONTENT --%>
            <table>
                <thead>
                <tr>
                    <td>N</td>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Amount</td>
                    <%--    <td>Check</td> --%>
                    <td>Delete</td>
                </tr>
                </thead>
                <c:set var="k" value="0"/>

                <%--    ${sessionScope.user} --%>

                <c:forEach var="item" items="${cart}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td class="quantity"><c:out value="${k}"/></td>
                        <td class="quantity">${item.key.name}</td>
                        <td class="quantity">${item.key.price}</td>
                        <td class="quantity">
                            <a class="delete-btn btn-incre"
                               href="${pageContext.request.contextPath}/controller?command=pageCart&action=inc&id=${item.key.id}"><i
                                    class="fas fa-plus-square"></i></a>
                            <input type="text" name="quantity" class="delete-btn" value="${item.value}" readonly>
                            <a class="delete-btn btn-decre"
                               href="${pageContext.request.contextPath}/controller?command=pageCart&action=dec&id=${item.key.id}"><i
                                    class="fas fa-minus-square"></i></a>
                        </td>
                        <td class="quantity">
                            <a class="delete-btn"
                               href="${pageContext.request.contextPath}/controller?command=pageCart&action=delete&id=${item.key.id}">
                                <i class="fas fa-squarespace">X</i>
                            </a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <%-- CONTENT _product_from_cart--%>
    </form>
    <br>
    <br>
    <h4 class="cart_totals">Total Price ${totalPrice}</h4>
    <br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="catalog">
        <button type="submit" value="catalog">Catalog of products</button>
    </form>
    <hr class="dropdown-divider">
    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
    <br>
</main>
<footer>
    <%@ include file="/footer.jspf" %>
    <%-- <script src="${pageContext.request.contextPath}/cart.js"></script> --%>
</footer>
</body>
</html>