function move() {

    viewport = new Viewport();
    mapNavigator = new MapNavigator();
    var offset = window.innerHeight;
    console.log(offset);
    var text = $('.wrap')[0];
    console.log(text);
    console.log($(text).position());
    $(text).offset({top: 150, left: 100});
    console.log(text);
    console.log($(text).offset());
    $(text).css('hidden', 'false');
    console.log(text);
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
        $(text).mouseout(function () {
            isPaused = false
        });
    }

    function frame() {
        if (isPaused) {
            return;
        }
        passed++;
        offset--;
        $(text).css('margin-top', offset + 'px');
        //currentWord = passed / text.clientHeight * $('#meta').attr('size');
        currentWord = passed / $(text).clientHeight * $('#meta').attr('size');
        activateTags();
        prevCurrentWord = currentWord;
        //if (offset == -text.clientHeight) {
        if (offset == -$(text).clientHeight) {
            console.log(offset);
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
            tag.addClass('backgroundHighlight');
            tag.hover(function () {
                $(this).addClass('backgroundHighlightSelected');
                places.getPlace($(this).attr('visualname')).mark();
            }, function () {
                $(this).removeClass('backgroundHighlightSelected');
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
