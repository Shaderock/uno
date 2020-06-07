<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <title>Welcome to the lobby</title>
    </head>
    <body>
        <p>Hi ${login}! Are you ready for game?</p>
        <a href="/startgame"> <input type="submit" value="Start game"/> </a>
        <br/>
        <a href="/logout"> <input type="submit" value="Change login"/> </a>
    </body>
</html>