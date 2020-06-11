function release(num){
    window.open("release?ordernumber=" + num, "_self");
}
function take(){
    window.open("take", "_self");
}
function color(colorEnum){
    window.open("color?color=" + colorEnum, "_self");
}
function skip(){
    window.open("skip", "_self");
}

document.addEventListener("DOMContentLoaded", function(event) {
    if (document.getElementById("leftPlayer") != document.getElementsByClassName("player_turn")[0]){
        window.setTimeout(window.location.reload.bind(window.location), 2000);
    }
});