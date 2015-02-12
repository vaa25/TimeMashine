function move() {
    var offset = window.innerHeight;
    var text = document.getElementById('text');
    var places = text.getElementsByTagName('place');
    var passed = 0;
    console.log(offset / text.clientHeight);
    var prevCurrentWord = 0;
    var currentWord = 0;
    var amount = document.getElementById('meta').getAttribute('size');
    var step = amount / text.clientHeight;
    function frame() {
        passed++;
        offset--;
        text.style.marginTop = offset + 'px';
        currentWord = passed / text.clientHeight * amount;
        activateTags();
        prevCurrentWord = currentWord;
        if (offset == -text.clientHeight) {
            clearInterval(timer);
        }
    }

    function activateTags() {
        for (var i = Math.round(prevCurrentWord); i < Math.round(currentWord); i++) {
            var tag = document.getElementById(i.toString());
            if (tag != null) {
                console.log(i);
                tag.getSelection();
                console.log(tag);
            }
        }
    }

    var timer = setInterval(frame, 10);

}