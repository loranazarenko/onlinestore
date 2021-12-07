<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Receipts</title>
    <%@ include file="/header.jspf" %>
</head>

<body>
<%@ include file="/headerbody.jspf" %>
<table id="main-container justify-content-center">
    <tr>
        <td class="content">
            <div class="p-3 row justify-content-center">
                <div class="container">
                    <c:if test="${sessionScope.user == null}">
                        <div class="alert alert-danger fade show " role="alert">
                            No Rights
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">N</th>
                                <th scope="col">OrderId</th>
                                <th scope="col">Description</th>
                                <th scope="col">Total</th>
                                <th scope="col">Status</th>
                                    <%--       <th scope="col">Pay</th>
                                           <th scope="col">Cancel</th> --%>
                            </tr>
                            </thead>
                            <c:set var="k" value="0"/>
                            <c:forEach var="userOrder" items="${receiptsStatuses}">
                                <c:set var="k" value="${k+1}"/>
                                <tr>
                                    <td><c:out value="${k}"/></td>
                                    <td><c:out value="${userOrder.key.id}"/></td>
                                    <td><c:out value="${userOrder.key.description}"/></td>
                                    <td><c:out value="${userOrder.key.total}"/></td>
                                    <c:if test="${userOrder.value == 'registered'}">
                                        <td class="text-success"><c:out value="${userOrder.value}"/></td>
                                    </c:if>
                                    <c:if test="${userOrder.value == 'paid'}">
                                        <td class="text-active"><c:out value="${userOrder.value}"/></td>
                                    </c:if>
                                    <c:if test="${userOrder.value == 'canceled'}">
                                        <td class="text-danger"><c:out value="${userOrder.value}"/></td>
                                    </c:if>
                                        <%--      <td>
                                                  <a class="btn btn-outline-dark mt-auto"
                                                     href="${pageContext.request.contextPath}/controller?command=userOrders&receiptId=${userOrder.key.id}&status=paid">
                                                      Pay receipt </a>
                                              </td>
                                              <td>
                                                  <a class="btn btn-outline-dark mt-auto"
                                                     href="${pageContext.request.contextPath}/controller?command=userOrders&receiptId=${userOrder.key.id}&status=canceled">
                                                      Cancel receipt </a>
                                              </td>  --%>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
            <a class="btn btn-outline-dark mt-auto" href="user.jsp"> Return to user page </a>
            <a class="btn btn-outline-dark mt-auto" href="index.jsp"> Return to enter </a>
        </td>
    </tr>
</table>
<%@ include file="/footer.jspf" %>
</body>
</html>
