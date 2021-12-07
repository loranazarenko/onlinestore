<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Order</title>
    <%@ include file="/header.jspf" %>
</head>
<body>
<header>
    <%@ include file="/headerbody.jspf" %>
</header>
<main>
    <div class="container">
        <div class="col-12 mx-auto my-lg-4 p-3 bg-light">
            <h1 class="display-3">Number ${order.id}</h1>
            <h3 class="lead">Status: ${order.statusId}</h3>
            <h3 class="lead">Description: ${order.description}</h3>
            <h3 class="lead">TOTAL: ${order.total}</h3>
            <div class="row">
                <c:forEach var="orderItem" items="${orderItems}">
                    <div class="col-sm-4">
                        <div class="card text-white bg-secondary card-margin-bottom">
                            <div>
                                <img style="object-fit: cover; height: 200px" class="card-img-top"
                                     src="assets/<c:out value="${orderItem.key.picture}"/>" alt="Card image">
                            </div>
                            <div class="card-body">
                                <h5 style="text-align: center"
                                    class="card-title">Name: ${orderItem.key.name}</h5>
                                <h5 style="text-align: center" class="card-title">Price: ${orderItem.key.getPrice()}
                                </h5>
                                <h5 style="text-align: center" class="card-title">Amount: ${orderItem.value}</h5>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${order.statusId==1}">
                <c:if test="${sessionScope.role == 'user'}">
                    <td>
                        <a class="btn btn-success nav-link btn-block"
                           href="${pageContext.request.contextPath}/controller?command=userOrders&receiptId=${order.id}&status=paid">
                            Payd receipt </a>
                    </td>
                    <td>
                        <a class="btn btn-danger nav-link btn-block"
                           href="${pageContext.request.contextPath}/controller?command=userOrders&receiptId=${order.id}&status=canceled">
                            Reject receipt </a>
                    </td>
                </c:if>
            </c:if>
        </div>
    </div>
</main>

<footer>
    <%@ include file="/footer.jspf" %>
</footer>

</body>
</html>