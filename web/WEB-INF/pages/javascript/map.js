/**
 * Created by vaa25 on 13.02.2015.
 */
var map;
var mapNavigator;

function MapNavigator() {
    var busy = false;
    var queue = [];
    this.smoothPanToBounds = function () {
        const zoomSpeedInterval = 300;
        var newElement = new Viewport();
        newElement.add(viewport.getLatLngBounds(), 'center');
        queue.push(newElement);
        if (busy) {
            return;
        }
        var currentViewport = queue.shift();
        var newZoom = currentViewport.getPreferZoom();
        var oldZoom = map.getZoom();
        var zoomStep = getZoomStep();
        var currentZoom = oldZoom;
        busy = true;
        var timer = setInterval(zoomTo, zoomSpeedInterval);

        function getZoomStep() {
            if (oldZoom < newZoom)return 1;
            if (oldZoom > newZoom)return -1;
            return 0;
        }

        function zoomTo() {
            currentZoom += zoomStep;
            map.setZoom(currentZoom);
            map.panTo(currentViewport.getCenter());
            if (currentZoom == newZoom) {
                nextOrExit();
            }

            function exit() {
                busy = false;
                clearInterval(timer);
            }

            function refresh() {
                oldZoom = newZoom;
                newZoom = currentViewport.getPreferZoom();
                zoomStep = getZoomStep();
            }

            function nextOrExit() {
                currentViewport = queue.shift();
                if (currentViewport == undefined) {
                    exit();
                } else {
                    refresh();
                }
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
    map = new google.maps.Map(document.getElementsByClassName('left')[0], mapOptions);
}