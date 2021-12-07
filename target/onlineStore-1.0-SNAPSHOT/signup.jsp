<html>
<head>
    <title>SIGN UP HERE</title>
 <%--   <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
    <%@ include file="/header.jspf" %>
</head>
<body>

<%@ include file="/headerbody.jspf" %>

<main>
    <div class="container">
        <div>
            <form id="login_form" action="controller" method="post">
                <input type="hidden" name="command" value="signup">
                <h1>
                    <div style="text-align: center;">Enter your data</div>
                </h1>
                <div class="form-group">
                    <input type="text" class="form-control" name="login" id="floatingLogin" placeholder="Login">
                    <label for="floatingLogin">Enter your login</label>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" id="floatingPassword"
                           placeholder="Password">
                    <label for="floatingPassword">Enter your password</label>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="name" id="floatingName" placeholder="You name">
                    <label for="floatingName">Enter your name</label>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" id="floatingEmail"
                           placeholder="name@example.com">
                    <label for="floatingEmail">Enter your email</label>
                </div>
                <button type="submit">Sing Up</button>
            </form>
        </div>
    </div>
    <hr>
    <br>
    <a href="index.jsp"> return to enter </a>
    <br>
</main>
<%@ include file="/footer.jspf" %>
</body>
</html>
