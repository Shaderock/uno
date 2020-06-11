<%@ page language="java" contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <title>Please, wait</title>
        <link rel="shortcut icon" href="/static/images/favicon.png" type="image/png">
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