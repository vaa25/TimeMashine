/**
 * Created by Б on 25.01.2015.
 */

var map;
var placeSources;
var places;

function Places() {
    this.places = [];
    this.addPlace = function (place) {
        this.places.push(place);
    };
    this.getPlace = function (name) {
        for (var index in this.places) {
            if (this.places.hasOwnProperty(index) && this.places[index].title == name) {
                return this.places[index];
            }
        }
    }
}
function initializePlaces() {
    places = new Places();
}

function setPlaceSources(source) {
    placeSources = source;
}

function getPlaceSource(name) {
    for (var index in placeSources) {
        if (placeSources.hasOwnProperty(index) && placeSources[index].title == name) {
            return placeSources[index];
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


