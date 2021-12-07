<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>

    <%--<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> --%>
    <%@ include file="/header.jspf" %>

</head>
<body>
<%@ include file="/headerbody.jspf" %>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.role != 'admin'}">
                <div class="alert alert-danger fade show " role="alert">
                    No rights
                </div>
            </c:if>
            <form id="delete_products" action="controller">
                <div class="container px-4 px-lg-5 my-3">
                    <input type="hidden" name="command" value="deleteProducts"/>
                    <h4><input type="submit" value='DELETE PRODUCTS'/></h4>
                </div>
                <br>
                <jsp:useBean id="listOfProducts" scope="session" type="java.util.List"/>
                <%--   <jsp:useBean id="listOfProducts" scope="session" type="java.util.List"/>--%>
                <c:forEach var="product" items="${listOfProducts}">

                    <div class="col mb-5" id="listOfProducts">
                        <div class="mb-3">
                            <!-- Product name-->
                            <h5 class="fw-bolder">${product.name}</h5>
                        </div>
                        <label>
                            <input type="checkbox" name="itemIds" value="${product.id}"/>
                        </label>
                    </div>
                </c:forEach>
            </form>
            <hr class="dropdown-divider">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/index.jsp">Home</a>
            <hr class="dropdown-divider">
            <div class="form-group">
                <form action="controller" method="get">
                    <input type="hidden" class="form-control" name="command" value="catalog">
                    <button type="submit" value="catalog">Catalog of products</button>
                </form>
            </div>
        </div>
    </div>
</div>


<%@ include file="/footer.jspf" %>
<!-- Bootstrap core JS-->
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script> --%>

</body>
</html>