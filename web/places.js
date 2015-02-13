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

function getPlace(name) {
    for (var index in places) {
        if (places.hasOwnProperty(index) && places[index].title == name) {
            return places[index];
        }
    }
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
function getLatLng(polygon) {
    return new google.maps.LatLng(polygon.y, polygon.x);
}


