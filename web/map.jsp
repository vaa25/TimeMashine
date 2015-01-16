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
        var polyline;
        var Latitude = null;
        var Longitude = null;
        var map = null;
        var path = [];


        function getLatLng(coord) {
            return new google.maps.LatLng(coord.latitude, coord.longitude);
        }
        var yerusalemLatLng = new google.maps.LatLng(
                ${yerusalem.latitude},
                ${yerusalem.longitude}
        );
        var zionLatLng = new google.maps.LatLng(
                ${zion.latitude},
                ${zion.longitude}
        );
        function initialize() {
            var mapOptions = {
                zoom: 15,
                center: yerusalemLatLng,
                mapTypeId: google.maps.MapTypeId.SATELLITE
            };

            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

            var path = [
                yerusalemLatLng,
                zionLatLng
            ];
            polyline = new google.maps.Polyline({
                path: path,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 2,
                map: map
            });

            polyline.setMap(map);

            var yerusalemMapLabel = new MapLabel({
                text: 'Yerusalem',
                position: yerusalemLatLng,
                map: map,
                fontSize: 35,
                align: 'right'
            });

            var yerusalemMarker = new google.maps.Marker;
            yerusalemMarker.bindTo('map', yerusalemMapLabel);
            yerusalemMarker.bindTo('position', yerusalemMapLabel);
            yerusalemMarker.setDraggable(false);

            var zionMapLabel = new MapLabel({
                text: 'Zion',
                position: zionLatLng,
                map: map,
                fontSize: 35,
                align: 'right'
            });

            var zionMarker = new google.maps.Marker;
            zionMarker.bindTo('map', zionMapLabel);
            zionMarker.bindTo('position', zionMapLabel);
            zionMarker.setDraggable(false);
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>