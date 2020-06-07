<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <title>Welcome to the lobby</title>
        <script>
            document.addEventListener("DOMContentLoaded", function(event) {
              window.setTimeout(window.location.reload.bind(window.location), 5000);
            });
        </script>
    </head>
    <body>
        <p>Waiting for other players</p>
        <a href="/endgame"> <input type="submit" value="Stop"> </a>
    </body>
</html>