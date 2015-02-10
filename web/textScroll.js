function move(offset) {
    var text = document.getElementById('text');
    console.log(offset / text.clientHeight);
    function frame() {
        offset--;
        text.style.marginTop = offset + 'px';
        geo = document.getElementById('1');
        if (offset == -text.clientHeight) {
            clearInterval(timer);
        }
    }

    var timer = setInterval(frame, 100);

}