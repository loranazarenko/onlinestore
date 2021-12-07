<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page is here!!!</title>
</head>
<body>

<h2>ERROR PAGE!</h2>

${requestScope['javax.servlet.error.message']}

</body>
</html>