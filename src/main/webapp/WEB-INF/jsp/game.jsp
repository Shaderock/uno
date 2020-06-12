<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>UNO game</title>
        <link rel="shortcut icon" href="/static/images/favicon.png" type="image/png">
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/static/css/game.css">
        <script src="/static/js/game.js"></script>
    </head>
    <body>
        <div id="game">
            <div class="game-field perspective ${topCardColor}">
                <div id="player" ${playerTurn}>
                    ${playerHand}
                </div>
                <div id="player_left" ${leftPlayerTurn}>
                    ${leftPlayerHand}
                </div>
                <div id="player_top" ${topPlayerTurn}>
                    ${topPlayerHand}
                </div>
                <div id="player_right" ${rightPlayerTurn}>
                    ${rightPlayerHand}
                </div>
                <div id="piles_area">
                    <div id="draw_pile" ${canDraw}>
                        ${drawPile}
                    </div>
                    <div id="discard_pile">
                        ${discardPile}
                    </div>
                </div>
                <div id="controls">
                    <div id="skip" ${canSkip} onclick="skip()">
                        <p><img id="skip_pic" src="/static/images/skip.jpg">
                        Skip turn</p>
                    </div>
                    <div id="endgame"  onclick="endgame()">
                        <p><img id="endgame_pic" src="/static/images/exit.jpg">
                        End game</p>
                    </div>
                    <br>
                </div>
            </div>
            <div id="dialog_background" ${canChooseColor}>
                <div id="dialog_container">
                    <br>
                    <b id="choose_title">Choose the color:</b>
                    <div id="colors_list">
                        <div class="card" id="red" onclick="color('red')">
                            <div class="bckg"></div>
                        </div>
                        <div class="card" id="blue" onclick="color('blue')">
                            <div class="bckg"></div>
                        </div>
                        <div class="card" id="yellow" onclick="color('yellow')">
                            <div class="bckg"></div>
                        </div>
                        <div class="card" id="green" onclick="color('green')">
                            <div class="bckg"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="original">
            <p>UI inspiration - <a target="_blank" rel="noopener noreferrer"
                                 href="https://codepen.io/tinhthanh">@tinhthanh.</a></p>
        </div>
    </body>
</html>