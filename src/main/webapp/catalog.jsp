<% if(request.getCharacterEncoding() == null) request.setCharacterEncoding("cp1251"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%-- <meta charset="UTF-8"> --%>
    <%@ page language="java" contentType="text/html;charset=cp1251" %>

    <title>Products</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet"/>

</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#listOfProducts">Products List</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
                <c:if test="${sessionScope.role == 'admin'}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin.jsp">Admin
                        Page</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 'user'}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user.jsp">User
                        Page</a></li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=catalog">All Products</a>
                        </li>
                        <li>
                            <hr class="dropdown-divider"/>
                        </li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=catalog&sort=category_id">Items
                            sort by category</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=catalog&sort=price">Items
                            sort by price</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=catalog&sort=color">Items
                            sort by color</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=catalog&sort=create_date">Items
                            sort by date of create</a></li>
                    </ul>
                </li>

                <c:if test="${sessionScope.role == 'admin'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
                           role="button"
                           aria-haspopup="true" aria-expanded="false">
                            Control
                        </a>
                        <div class="dropdown-menu" aria-labelledby="userDropdown">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=addProduct">Add Product</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=deleteProducts">Delete
                                Products</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=adminUserOrders">View
                                Orders</a>
                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                </c:if>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
                       role="button"
                       aria-haspopup="true" aria-expanded="false">
                        Language
                    </a>
                    <div class="dropdown-menu" aria-labelledby="userDropdown">
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=catalog&currentLang=en">English</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=catalog&currentLang=ru">Russian</a>
                    </div>
                </li>
            </ul>

            <div style="margin-right: auto;margin-left: auto" class="text-center text-black">
                Account: ${sessionScope.user.login}</div>

            <c:if test="${sessionScope.role != 'admin'}">
                <form class="d-flex" action="controller" method="get">
                    <input type="hidden" name="command" value="pageCart">
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="bi-cart-fill me-1"></i>
                        Cart
                        <span class="badge bg-dark text-white ms-1 rounded-pill">
                       <jsp:useBean id="currentCount" scope="session" type="java.lang.Integer"/>
                       <c:if test="${currentCount != null}">
                           ${currentCount}
                       </c:if>
                        <c:if test="${currentCount == null}">
                            0
                        </c:if>
                    </span>
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">OnlineStore in style</h1>
            <p class="lead fw-normal text-white-50 mb-0">With this shop homepage template</p>
        </div>
    </div>
</header>
<!-- Section-->

<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

            <jsp:useBean id="listOfProducts" scope="session" type="java.util.List"/>
            <c:forEach var="product" items="${listOfProducts}">

                <div class="col mb-5" id="listOfProducts">
                    <div class="card h-100">
                        <div class="card-body p-4">
                            <div class="text-center">
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder"
                                            style="max-width: 10ch; max-height: 5ch;">${product.name}</h5>

                                    </div>
                                </div>
                                <!-- Product image-->
                                <img class="card-img-top"
                                     src="assets/<c:out value="${product.picture}"/>"
                                     width="150"
                                     height="150" alt="picture">
                                <!-- Product price-->
                                    ${product.price}
                                <h5>Available quantity</h5>
                                    ${product.count}
                                <h5>Description</h5>
                                    ${product.description}
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center">
                                        <c:if test="${sessionScope.role == 'admin'}">
                                            <a class="btn btn-outline-dark mt-auto"
                                               href="${pageContext.request.contextPath}/controller?command=editProduct&productId=${product.id}">
                                                View options and edit </a>
                                        </c:if>
                                    </div>
                                </div>

                                <c:if test="${sessionScope.role != 'admin'}">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="addToBasket">
                                        <div role="group">
                                        <%--    <input type="number" step="1" min="1"
                                                   max="100"
                                                   id="num_count"
                                                   name="count" value="1" title="Qty"> --%>
                                            <button type="submit" name="productId"
                                                    value="${product.id}">
                                                Add product to the Basket
                                            </button>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div style="margin: 0 auto;" class="text-center text-black">
        <%--For displaying Previous link except for the 1st page --%>
        <jsp:useBean id="currentPage" scope="session" type="java.lang.Integer"/>
        <c:if test="${currentPage != 1}">
            <td><a href="${pageContext.request.contextPath}/controller?command=catalog&currentPage=${currentPage - 1}">Previous</a>
            </td>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page border="1" cellpadding="5" cellspacing="5"  --%>
        <table style="margin: 0 auto; border: 0.1rem dotted; padding: 5px; border-collapse: separate; border-spacing: 5px;">
            <tr>
                <jsp:useBean id="noOfPages" scope="session" type="java.lang.Integer"/>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=catalog&currentPage=${i}">${i}</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="${pageContext.request.contextPath}/controller?command=catalog&currentPage=${currentPage + 1}">Next</a>
            </td>
        </c:if>
    </div>
</section>

<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Larisa Website 2021</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>