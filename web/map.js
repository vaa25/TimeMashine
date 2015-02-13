/**
 * Created by vaa25 on 13.02.2015.
 */
var map;

function smoothPanToBounds(newValue, latLngBounds) {
    var oldValue = map.getZoom();
    var step = getStep();
    var value = oldValue;
    var queue = [];
    queue.push({
        zoom: newValue,
        bound: latLngBounds
    });
    console.log(oldValue);
    console.log(newValue);

    function getStep() {
        if (oldValue < newValue)return 1;
        if (oldValue > newValue)return -1;
        return 0;
    }

    function zoomTo() {
        value += step;
        console.log(value);
        map.setZoom(value);
        map.panToBounds(latLngBounds);
        if (value == newValue) {

            clearInterval(timer);
        }
    }

    var timer = setInterval(zoomTo, 1000);
}
function initializeMap(zoom, position) {
    var mapOptions = {
        zoom: zoom,
        center: getLatLng(position),
        mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
}