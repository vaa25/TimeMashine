function move() {
    viewport = new Viewport();
    var offset = window.innerHeight;
    var text = document.getElementById('text');
    text.style.marginTop = offset + 'px';
    text.hidden = false;
    var passed = 0;
    var prevCurrentWord = 0;
    var currentWord = 0;
    var meta = document.getElementById('meta');
    var size = meta.getAttribute('size');
    var startZoom = JSON.parse(meta.getAttribute('zoom'));
    var startPosition = JSON.parse(meta.getAttribute('position'));
    var isPaused = false;

    setMouseControl();

    initializeMap(startZoom, startPosition);
    initializePlaces();
    var timer = setInterval(frame, 10);

    function setMouseControl() {
        text.onmouseover = function () {
            isPaused = true
        };
        text.onmouseout = function () {
            isPaused = false
        };
    }

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
                    tag.setAttribute('style', 'background:grey');
                    tag.setAttribute('onmouseover', 'markBound(this)');
                    tag.setAttribute('onmouseout', 'unmarkBound(this)');
                    var place = places.getPlace(tag.getAttribute('visualname'));
                    if (place == undefined) {
                        var placeSource = getPlaceSource(tag.getAttribute('placename'));
                        placeSource.title = tag.getAttribute('visualname');
                        place = new Place(placeSource);
                        places.addPlace(place);
                        place.draw();
                    }
                    console.log(place);
                    viewport.add(place.latLngBounds, tag.getAttribute('panto'));
                    console.log(viewport);
                    console.log(tag.getAttribute('panto'));
                    smoothPanToBounds();
                }
            }
        }
    }
}
function markBound(placeTag) {
    places.getPlace(placeTag.getAttribute('visualname')).mark();
}
function unmarkBound(placeTag) {
    places.getPlace(placeTag.getAttribute('visualname')).unmark();
}