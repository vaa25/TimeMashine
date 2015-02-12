/**
 * Created by Б on 25.01.2015.
 */

var map;
var places;

function initializeMap(zoom, position) {
    var mapOptions = {
        zoom: zoom,
        center: getLatLng(position),
        mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
}

function setPlaces(source) {
    places = source;
}

function showPlace(place) {
    getMarker(place);
    getBound(place);
}

function addPlaceToLatLngBound(latLngBound, place) {
    var bound = getLatLons(place.bound);
    for (var index in bound) {
        if (bound.hasOwnProperty(index)) {
            latLngBound.extend(bound[index]);
        }
    }
    return latLngBound;
}
function getLatLngBound(place) {
    var center = getLatLng(place.center);
    var latLngBound = new google.maps.LatLngBounds(center, center);
    addPlaceToLatLngBound(latLngBound, place);
    return latLngBound;
}
function getPlace(name) {
    for (var index in places) {
        if (places.hasOwnProperty(index) && places[index].title == name) {
            return places[index];
        }
    }
}

function getMarkers(places) {
    var markers = [];
    places.forEach(function (place) {
        markers.push(getMarker(place));
    });
    return markers;
}
function getPolygons(places) {
    var bound = [];
    for (var index in places) {
        if (places.hasOwnProperty(index)) {
            places[bound.length] = getBound(places[index]);
        }
    }
}

function getBound(place) {
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
function log(value) {
    console.log(value);
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


