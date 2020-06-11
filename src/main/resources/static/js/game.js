function release(num){
    window.open("game/release?ordernumber=" + num, "_self");
}
function take(){
    window.open("game/take", "_self");
}
function color(colorEnum){
    window.open("game/color?color=" + colorEnum, "_self");
}
function skip(){
    window.open("game/skip", "_self");
}

function endgame(){
    window.open("game/endgame", "_self");
}

document.addEventListener("DOMContentLoaded", function(event) {
    if (document.getElementById("player") != document.getElementsByClassName("player_turn")[0]){
        window.setTimeout(window.location.reload.bind(window.location), 2000);
    }
});