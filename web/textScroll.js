function move(offset) {
    console.log();
    function frame() {
        offset--;
        document.getElementById('text').style.marginTop = offset + 'px';
        if (offset == -document.getElementById('text').clientHeight) {
            clearInterval(timer);
        }
    }

    var timer = setInterval(frame, 100);

}