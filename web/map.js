/**
 * Created by vaa25 on 13.02.2015.
 */
var map;
var queue = [];
var busy = false;
function smoothPanToBounds(newZoom, latLngBounds) {

    if (busy) {
        queue.push({
            zoom: newZoom,
            bound: latLngBounds
        });
        return;
    }

    var oldZoom = map.getZoom();
    var step = getStep();
    var value = oldZoom;
    busy = true;
    var timer = setInterval(zoomTo, 300);

    function getStep() {
        if (oldZoom < newZoom)return 1;
        if (oldZoom > newZoom)return -1;
        return 0;
    }

    function zoomTo() {
        value += step;
        map.setZoom(value);
        map.panToBounds(latLngBounds);
        if (value == newZoom) {
            nextOrExit();
        }

        function exit() {
            busy = false;
            clearInterval(timer);
        }

        function refresh(data) {
            oldZoom = newZoom;
            newZoom = data.zoom;
            latLngBounds = data.bound;
            step = getStep();
        }

        function nextOrExit() {
            var data = queue.shift();
            if (data == null) {
                exit();
            } else {
                refresh(data);
            }
        }
    }
}

function initializeMap(zoom, position) {
    var mapOptions = {
        zoom: zoom,
        center: getLatLng(position),
        mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
}