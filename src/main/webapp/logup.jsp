<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>

<html>
<c:set var="title" value="Login page" scope="page"/>

<body class="text-center">

<main style="padding-top: 10%">
    <div>
        <div>
            <form id="login_form" action="controller" method="post">
                <input type="hidden" name="command" value="logup">
                <h1>Enter your data</h1>
                <div>
                    <input type="text" name="login" id="floatingLogin" placeholder="Login">
                    <label for="floatingLogin">Enter your login</label>
                </div>
                <div>
                    <input type="password" name="password" id="floatingPassword" placeholder="Password">
                    <label for="floatingPassword">Enter your password</label>
                </div>
                <div>
                    <input type="text" name="name" id="floatingName" placeholder="You name">
                    <label for="floatingName">Enter your name</label>
                </div>
                <div>
                    <input type="email" name="email" id="floatingEmail" placeholder="name@example.com">
                    <label for="floatingEmail">Enter your email</label>
                </div>
                <button type="submit">Sing Up</button>
            </form>
        </div>
        <div></div>
    </div>
</main>
</body>
</html>
