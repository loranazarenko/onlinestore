<% if(request.getCharacterEncoding() == null) request.setCharacterEncoding("cp1251"); %>
<%@ page language="java" contentType="text/html;charset=cp1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <title>Add product | OnlineStore</title>
    <%@ include file="/header.jspf" %>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/headerbody.jspf" %>
<main>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6 my-sm-3">
                <c:if test="${sessionScope.role != 'admin'}">
                    <div class="alert alert-danger fade show " role="alert">
                        No rights
                    </div>
                </c:if>
                <c:if test="${sessionScope.role == 'admin'}">
                    <c:if test="${param.message == 'error'}">
                        <div class="alert alert-danger fade show " role="alert">
                            Product adding error
                        </div>
                    </c:if>
                    <c:if test="${param.message == 'ok'}">
                        <div class="alert alert-success fade show " role="alert">
                            product added successfully
                        </div>
                    </c:if>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="confirmAddProduct">
                        <h4>Enter Product Details</h4>
                        <div class="mb-3">
                            <label for="title" class="form-label">Name Of Product</label>
                            <input type="text" class="form-control" name="product-name" id="title"
                                   placeholder="Name Of Product" required>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm mb-3">
                                <label for="photo" class="form-label">Product Photo</label>
                                <input type="text" id="photo" name="photo" class="form-control"
                                       placeholder="Photo" required>
                            </div>
                            <div class="col-sm">
                                <label for="price" class="form-label">Price</label>
                                <input type="text" id="price" name="price" class="form-control"
                                       placeholder="999.99" pattern="^\d+\.\d{0,2}$" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm mb-3">
                                <jsp:useBean id="categories" scope="session"
                                             type="java.util.ArrayList<com.epam.onlinestore.entity.Category>"/>
                                <label for="category" class="form-label">Category</label>
                                <select class="form-control" id="category" name="category">
                                    <c:forEach var="category" items="${categories}">
                                        <option>${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm">
                                <fieldset class="form-group">
                                    <div class="form-check">
                                        <input type="checkbox" name="isDeleted" class="form-check-input"
                                               id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Deleted</label>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="col-sm mb-3">
                                <label for="color" class="form-label">Color</label>
                                <input type="text" name="color" id="color" class="form-control"
                                       placeholder="color" required>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="mb-2">
                                <label for="descriptionEn" class="form-label">Description En</label>
                                <input type="text" class="form-control" name="descriptionEn" id="descriptionEn"
                                       placeholder="Description En" required>
                            </div>
                            <div class="mb-2">
                                <label for="descriptionRu" class="form-label">Description Ru</label>
                                <input type="text" class="form-control" name="descriptionRu" id="descriptionRu"
                                       placeholder="Description Ru" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="count" class="form-label">Count</label>
                            <input type="text" name="count" id="count" class="form-control"
                                   placeholder="count" required>
                        </div>

                        <div class="mb-3">
                            <label for="create_date" class="form-label">Date of the creating</label>
                            <input type="date" name="create_date" id="create_date" class="form-control"
                                   placeholder="create_date" required>
                        </div>

                        <button class="btn btn-primary" type="submit">Add</button>
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
                </c:if>
            </div>
        </div>
    </div>
</main>

<%@ include file="/footer.jspf" %>
</body>

</html>