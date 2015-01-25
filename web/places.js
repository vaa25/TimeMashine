/**
 * Created by Б on 25.01.2015.
 */
function initialize(places) {
    var mapOptions = {
        zoom: 15,
        center: getLatLng(places[0].center),
        mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    var markers = getMarkers(places);
    var bound = getPolygons(places);

    function getMarkers(places) {
        var markers = [];
        for (var index in places) {
            if (places.hasOwnProperty(index)) {
                markers[markers.length] = getMarker(places[index]);
            }
        }
    }

    function getPolygons(places) {
        var bound = [];
        for (var index in places) {
            if (places.hasOwnProperty(index)) {
                places[bound.length] = getPolygon(places[index]);
            }
        }
    }

    function getPolygon(place) {
        return new google.maps.Polygon({
            path: getLatLons(place.bound),
            strokeColor: "#FF0000",
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: "#FF0000",
            fillOpacity: 0.35,
            map: map
        });
    }

    function getLatLons(polygons) {
        var latLon = [];
        for (var polygon in polygons) {
            if (polygons.hasOwnProperty(polygon)) {
                latLon[latLon.length] = getLatLng(polygons[polygon]);
            }
        }
        return latLon;
    }

    function getMarker(place) {
        var marker = new google.maps.Marker;
        var mapLabel = getMapLabel(place);
        marker.bindTo('map', mapLabel);
        marker.bindTo('position', mapLabel);
        marker.setDraggable(false);
        return marker;
    }

    function getLatLng(polygon) {
        return new google.maps.LatLng(polygon.y, polygon.x);
    }

    function getMapLabel(place) {
        return new MapLabel({
            text: place.title,
            position: getLatLng(place.center),
            map: map,
            fontSize: 16,
            align: 'right'
        });
    }

    function getMarkers(places) {
        var markers = [];
        places.forEach(function (e) {
            markers.push(getMarker(e));
        });
        return markers;
    }
}

