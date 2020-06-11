<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <title>Welcome to UNO game</title>
        <link rel="shortcut icon" href="/static/images/favicon.png" type="image/png">
    </head>
    <body>
        <form action="/login" method="get">
            <label for="login">Login
                ${authAttempt}
            </label>
            <input type="text" name="login">
            <input type="submit">
        </form>
        <br>
        <img src="static/exit.png">
    </body>
</html>