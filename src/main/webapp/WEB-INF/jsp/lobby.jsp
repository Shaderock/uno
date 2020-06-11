<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <title>Welcome to the lobby</title>
        <link rel="shortcut icon" href="/static/images/favicon.png" type="image/png">
    </head>
    <body>
        <p>Hi ${login}! Are you ready for game?</p>
        <a href="/startgame"> <input type="submit" value="Start game"/> </a>
        <br/>
        <a href="/logout"> <input type="submit" value="Change login"/> </a>
    </body>
</html>