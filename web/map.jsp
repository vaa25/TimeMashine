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
            var points =${polyline};
            var mapOptions = {
                zoom: 15,
                center: getLatLng(points[0]),
                mapTypeId: google.maps.MapTypeId.SATELLITE
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            var markers = getMarkers(points);
            var line = getPolyline(points);
            var yerusalem =${yerusalem};
            markers.add(getMarker(yerusalem));

            function getLatLng(place) {
                return getLatLng(place.center);
            }
            function getLatLng(coord) {
                return new google.maps.LatLng(coord.latitude, coord.longitude);
            }

            function getPolyline(points) {
                return new google.maps.Polyline({
                    path: getPath(points),
                    strokeColor: '#FF0000',
                    strokeOpacity: 1.0,
                    strokeWeight: 2,
                    map: map
                });
            }

            function getMapLabel(point) {
                return new MapLabel({
                    text: getTitle(point),
                    position: getLatLng(point),
                    map: map,
                    fontSize: 35,
                    align: 'right'
                });
            }

            function getMarker(point) {
                var marker = new google.maps.Marker;
                var mapLabel = getMapLabel(point);
                marker.bindTo('map', mapLabel);
                marker.bindTo('position', mapLabel);
                marker.setDraggable(false);
                return marker;
            }

            function getMarkers(points) {
                var markers = [];
                points.forEach(function (e) {
                    markers.push(getMarker(e));
                });
                return markers;
            }

            function getPoints(poly) {
                var points = [];
                poly.forEach(function (e) {
                    points.push(e);
                });
                return points;
            }

            function getPath(points) {
                var path = [];
                points.forEach(function (e) {
                    path.push(getLatLng(e));
                });
                return path;
            }

            function getTitle(point) {
                return point.title;
            }
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>