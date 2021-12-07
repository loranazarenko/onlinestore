<% if(request.getCharacterEncoding() == null) request.setCharacterEncoding("cp1251"); %>
<%@ page language="java" contentType="text/html;charset=cp1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <title>Edit product | Online-store</title>
    <%@ include file="/header.jspf" %>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/headerbody.jspf" %>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.role != 'admin'}">
                <div class="alert alert-danger fade show" role="alert">
                    No Rights
                </div>
            </c:if>
            <c:if test="${sessionScope.role == 'admin'}">
                <jsp:useBean id="product" scope="session" type="com.epam.onlinestore.entity.Product"/>
                <jsp:useBean id="category" scope="session" type="com.epam.onlinestore.entity.Category"/>
                <form action="${pageContext.request.contextPath}/controller?command=confirmEditProduct" method="post">

                    <h4 class="row justify-content-center">Edit Product Details</h4>
                    <br>
                    <div class="col-lg-auto mb-3">
                        <label for="title" class="form-label">Name Of Product</label>
                        <input type="text" class="form-control" name="product-name" id="title"
                               placeholder="Name Of Product" required
                               value="${product.name}">
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm mb-3">
                            <label for="photo" class="form-label">Product Photo</label>
                            <input type="text" id="photo" name="photo" class="form-control"
                                   placeholder="Name Of Product and product photo" required
                                   value="${product.picture}">
                        </div>

                        <div class="col-sm">
                            <label for="price" class="form-label">Price</label>
                            <input type="text" id="price" name="price" class="form-control"
                                   placeholder="999.99" pattern="^\d+\.\d{0,2}$" required
                                   value="${product.price}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-lg-auto mb-3">
                            <jsp:useBean id="categories" scope="session"
                                         type="java.util.ArrayList<com.epam.onlinestore.entity.Category>"/>
                            <label for="category" class="form-label">Category</label>
                            <select class="form-control" id="category" name="category">
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id}" ${category.id eq product.categoryId ? 'selected="selected"' : ''}>${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-sm">
                            <fieldset class="form-group">
                                <div class="form-check">
                                    <input type="checkbox" name="isDelete" class="form-check-input" id="exampleCheck1"
                                    <c:if test="${product.is_deleted == true}">
                                           checked
                                    </c:if>
                                    <label class="form-check-label" for="exampleCheck1">Deleted</label>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-sm mb-3">
                            <label for="color" class="form-label">Color</label>
                            <input type="text" name="color" id="color" class="form-control"
                                   placeholder="color" required
                                   value="${product.color}">
                        </div>
                    </div>

                    <div class="col-lg-auto mb-3">
                        <label for="descriptionEn" class="form-label">Description En</label>
                        <textarea class="form-control" name="descriptionEn" id="descriptionEn"
                                  placeholder="Description En" required><c:out value="${product.description}"/>
                        </textarea>
                        <label for="descriptionRu" class="form-label">Description Ru</label>
                        <textarea class="form-control" name="descriptionRu" id="descriptionRu"
                                  placeholder="description Ru" required>
                            <jsp:useBean id="langRu" scope="session" type="java.lang.String"/>
                                  <c:out value="${langRu}"/>
                        </textarea>

                        <div class="col-sm mb-3">
                            <label for="count" class="form-label">Count</label>
                            <input type="text" name="count" id="count" class="form-control"
                                   placeholder="count" required
                                   value="${product.count}">
                        </div>

                        <div class="col-sm mb-3">
                            <label for="create_date" class="form-label">Date of the creating</label>
                            <input type="date" name="create_date" id="create_date" class="form-control"
                                   placeholder="create_date" required
                                   value="${product.create_date}">
                        </div>
                    </div>

                    <input type="hidden" name="productId" value="${product.id}">
                    <button class="btn btn-primary" type="submit">Confirm</button>
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
<%@ include file="/footer.jspf" %>
</body>
</html>
