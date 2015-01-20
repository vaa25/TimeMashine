<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Simple click event</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
        html, body, #map-canvas {
            width: 80%;
            height: 100%;
            margin: 0px;
            padding: 0px
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/maplabel/src/maplabel-compiled.js"></script>
    <script type="text/javascript">
        function initialize() {
            var places =${places};
            var mapOptions = {
                zoom: 15,
                center: getLatLng(places[0].center),
                mapTypeId: google.maps.MapTypeId.SATELLITE
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            var markers = getMarkers(places);
            var polygons = getPolygons(places);

            function getMarkers(places) {
                var markers = [];
                for (var index in places) {
                    if (places.hasOwnProperty(index)) {
                        markers[markers.length] = getMarker(places[index]);
                    }
                }
            }

            function getPolygons(places) {
                var polygons = [];
                for (var index in places) {
                    if (places.hasOwnProperty(index)) {
                        places[polygons.length] = getPolygon(places[index]);
                    }
                }
            }

            function getPolygon(place) {
                return new google.maps.Polygon({
                    path: getLatLons(place.polygons),
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
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>