function move() {
    viewport = new Viewport();
    mapNavigator = new MapNavigator();
    var offset = window.innerHeight;
    var text = document.getElementById('text');
    text.style.marginTop = offset + 'px';
    text.hidden = false;
    var passed = 0;
    var prevCurrentWord = 0;
    var currentWord = 0;
    var isPaused = false;

    setMouseControl();

    initializeMap(JSON.parse($('#meta').attr('zoom')), JSON.parse($('#meta').attr('position')));
    initializePlaces();
    var timer = setInterval(frame, 10);

    function setMouseControl() {
        $(text).mouseover(function () {
            isPaused = true
        });
        $(text).mouseout = (function () {
            isPaused = false
        });
    }

    function frame() {
        if (isPaused) {
            return;
        }
        passed++;
        offset--;
        $(text).css('marginTop', offset + 'px');
        currentWord = passed / text.clientHeight * $('#meta').attr('size');
        activateTags();
        prevCurrentWord = currentWord;
        if (offset == -text.clientHeight) {
            clearInterval(timer);
        }
    }

    function activateTags() {
        function activateNewPlace() {
            var placeSource = getPlaceSource(tag.attr('placename'));
            placeSource.title = tag.attr('visualname');
            place = new Place(placeSource);
            places.addPlace(place);
        }

        function setTagControl() {
            tag.css('background', 'grey');
            tag.mouseover(function () {
                places.getPlace($(this).attr('visualname')).mark();
            });
            tag.mouseout(function () {
                places.getPlace($(this).attr('visualname')).unmark();
            });
        }

        for (var i = Math.round(prevCurrentWord); i < Math.round(currentWord); i++) {
            var tag = $('PLACE#' + i.toString());
            if (tag.length != 0) {
                setTagControl();
                var place = places.getPlace(tag.attr('visualname'));
                if (place == undefined) {
                    activateNewPlace();
                }
                viewport.add(place.latLngBounds, tag.attr('panto'));
                mapNavigator.smoothPanToBounds();
            }
        }
    }
}
