<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <%@ include file="/header.jspf" %>
</head>

<body>
<%@ include file="/headerbody.jspf" %>

<jsp:useBean id="userList" scope="session" type="java.util.List"/>
<c:forEach var="item_user" items="${userList}">
    <div class="container-fluid">
        <div class="row">
            <div class="col-10">
                <div class="row">
                    <div class="col my-1">
                        <aside class="blog-sidebar rounded shadow-lg">
                            <div class="flex-shrink-0 p-3 bg-white">
                                <ul class="list-unstyled ps-0">
                                    <li class="mb-1">
                                        <!--Button status start-->
                                        <div class="row">
                                            <div class="col">
                                                <div class="row">
                                                    <div class="col">
                                                        Login:
                                                        <b>${item_user.login}</b>
                                                    </div>
                                                </div>
                                                <span class="row">
                                                    <span class="col">
                                                        Password: <b>${item_user.password}</b>
                                                    </span>
                                                        <jsp:useBean id="userStatuses" scope="session"
                                                                     type="java.util.HashMap"/>
                                                      <label for="statuses" class="form-label">Status</label>
                                                        <select class="form-control" id="statuses" name="statuses">
                                                            <c:forEach items="${userStatuses}" var="statuses">
                                                                <option value="${statuses.key}" ${statuses.key eq item_user.getUserStatuses() ? 'selected="selected"' : ''}>${statuses.value}</option>
                                                            </c:forEach>
                                                       </select>
                                                        <span>
                                                            <a href="${pageContext.request.contextPath}/controller?command=userOrders&userid=${item_user.id}">
                                                            User orders
                                                            </a>
                                                        </span>
                                                        <span>
                                                              <a href="${pageContext.request.contextPath}/controller?command=adminUsers&action=update&status=${item_user.getUserStatuses()}&param=${item_user.login}">
                                                                Confirm change of the status of user
                                                              </a>
                                                        </span>
                                                </span>
                                            </div>
                                        </div>
                                    <li class="border-top my-3"></li>
                                </ul>
                            </div>
                        </aside>
                    </div>
                </div>
            </div>
        </div>

    </div>
</c:forEach>

<%@ include file="/footer.jspf" %>
</body>
</html>
