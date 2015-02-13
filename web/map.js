/**
 * Created by vaa25 on 13.02.2015.
 */
var map;
var queue = [];
var busy = false;
function smoothPanToBounds(latLngBounds) {
    var newZoom = getZoom();
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

    function getZoom() {
        var width = latLngBounds.getNorthEast().lat() - latLngBounds.getSouthWest().lat();
        var height = latLngBounds.getNorthEast().lng() - latLngBounds.getSouthWest().lng();
        log(width);
        log(height);
        var res = Math.round(14 - Math.log(Math.max(width, height) / 0.025) / Math.log(2));
        log(res);
        return res;
    }
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
            latLngBounds = data.bound;
            newZoom = getZoom();
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