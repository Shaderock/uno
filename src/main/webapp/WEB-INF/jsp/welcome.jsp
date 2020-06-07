<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <title>Welcome to the game</title>
    </head>
    <body>
        <form action="/login" method="get">
            <label for="login">Login
                ${authAttempt}
            </label>
            <input type="text" name="login">
            <input type="submit">
        </form>
    </body>
</html>