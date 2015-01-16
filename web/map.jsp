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
    <script type="text/javascript" src="../maplabel.js"></script>
    <script type="text/javascript">
        var poly;
        var Latitude = null;
        var Longitude = null;
        var map = null;
        var path = [];
        var yerusalem = new google.maps.LatLng(
                ${yerusalem.latitude},
                ${yerusalem.longitude}
        );

        function initialize() {
            var mapOptions = {
                zoom: 2,
                center: new google.maps.LatLng(0, 0),
                mapTypeId: google.maps.MapTypeId.SATELLITE
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            poly = new google.maps.Polyline({
                path: path,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 2,
                map: map
            });
            poly.setMap(map);

            var mapLabel = new MapLabel({
                text: 'Yerusalem',
                position: yerusalem,
                map: map,
                fontSize: 35,
                align: 'right'
            });

            var marker = new google.maps.Marker;
            marker.bindTo('map', mapLabel);
            marker.bindTo('position', mapLabel);
            marker.setDraggable(false);

            var myVar = setInterval(function () {
                updateposition()
            }, 200);

        }

        function updateposition() {
            path.push(yerusalem);
            if (path.length == 1) {
                map.setCenter(path[0]);
                map.setZoom(8);
            } else {
                map.setCenter(path[path.length - 1]);
            }
            poly.setPath(path);
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>