<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="m" %>
<!DOCTYPE html>
<html>
<%--<head>
    <meta charset="UTF-8">
    <title>SIGN UP HERE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>--%>

<head>
    <title>WELCOME PAGE</title>
    <%@ include file="/header.jspf" %>
    <div style="text-align: center;"> Current Date and Time is: <m:today/></div>
</head>
<body>

 <%@ include file="/headerbody.jspf" %>
 <div style="margin-right: auto;margin-left: auto" class="text-center text-black">
    <%--  <h2>
          <div style="text-align: center;">WELCOME TO THE ONLINE STORE!!!</div>
      </h2>--%>
    <main>
        <div class="container">
            <div>
                <br>
                <div class="form-group">
                    <form action="controller" method="get">
                        <input type="hidden" class="form-control" name="command" value="catalog">
                        <button type="submit" value="catalog">CATALOG OF PRODUCTS</button>
                    </form>
                </div>
                <br>
                <hr>
                <div class="form-group">
                    <form action="controller" method="post">
                        <input type="hidden" class="form-control" name="command" value="login">
                        <label>
                            <input name="login">
                        </label><br>
                        <label>
                            <input type="password" name="password">
                        </label><br>
                        <input type="submit" value="LOGIN">
                    </form>
                </div>
                <br>
                <hr>
                <div class="form-group">
                    <form action="controller" method="get">
                        <input type="hidden" class="form-control" name="command" value="signup">
                        <button type="submit" value="signup">SIGNUP</button>
                    </form>
                </div>

                <br>
                <hr>
                <div class="form-group">
                    <form action="controller" method="get">
                        <input type="hidden" class="form-control" name="command" value="logout">
                        <button type="submit" value="signup">LOGOUT</button>
                    </form>
                </div>
            </div>
        </div>
        <hr>
        <br>
    </main>
</div>
<%@ include file="/footer.jspf" %>
</body>
</html>