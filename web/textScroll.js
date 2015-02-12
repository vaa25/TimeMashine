function move() {
    var offset = window.innerHeight;
    var text = document.getElementById('text');

    var passed = 0;
    var prevCurrentWord = 0;
    var currentWord = 0;

    var meta = document.getElementById('meta');
    var size = meta.getAttribute('size');
    var startZoom = JSON.parse(meta.getAttribute('zoom'));
    var startPosition = JSON.parse(meta.getAttribute('position'));

    var isPaused = false;

    text.onmouseover = function () {
        isPaused = true
    };
    text.onmouseout = function () {
        isPaused = false
    };

    initializeMap(startZoom, startPosition);

    function frame() {
        if (isPaused) {
            return;
        }
        passed++;
        offset--;
        text.style.marginTop = offset + 'px';
        currentWord = passed / text.clientHeight * size;
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
                if (tag.tagName == 'PLACE') {
                    var place = getPlace(tag.getAttribute('placename'));
                    console.log(place);
                    place.title = tag.getAttribute('visualname');
                    place = new Place(place);
                    place.draw();
                    place.show();
                }
            }
        }
    }

    var timer = setInterval(frame, 10);

}