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
    <script type="text/javascript" src="../src/maplabel-compiled.js"></script>
    <script type="text/javascript">
        var poly;
        var Latitude = null;
        var Longitude = null;
        var map = null;
        var path = [];
        var yerusalem = new google.maps.LatLng(31.76904, 35.21633);
        function init() {

            var mapLabel = new MapLabel({
                text: 'Yerusalem',
                position: yerusalem,
                map: map,
                fontSize: 35,
                align: 'right'
            });
//            mapLabel.set('position', new google.maps.LatLng(34.03, -118.235));

            var marker = new google.maps.Marker;
            marker.bindTo('map', mapLabel);
            marker.bindTo('position', mapLabel);
            marker.setDraggable(false);

//            mapLabel.set('align', document.getElementById('align').value);
//            mapLabel.set('fontSize', document.getElementById('font').value);
//            mapLabel.set('fontColor', document.getElementById('font-color').value);
//            mapLabel.set('text', document.getElementById('text').value);
//            mapLabel.set('strokeWeight',document.getElementById('stroke-weight').value);
//            mapLabel.set('strokeColor',document.getElementById('stroke-color').value);
        }

        //        google.maps.event.addDomListener(window, 'load', init);


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
                strokeWeight: 2
            });

            poly.setMap(map);

            var mapLabel = new MapLabel({
                text: 'Yerusalem',
                position: yerusalem,
                map: map,
                fontSize: 35,
                align: 'right'
            });
//            mapLabel.set('position', new google.maps.LatLng(34.03, -118.235));

//        var marker = new google.maps.Marker;
//        marker.bindTo('map', mapLabel);
//        marker.bindTo('position', mapLabel);
//        marker.setDraggable(false);

            var myVar = setInterval(function () {
                updateposition()
            }, 200);

        }
        //create map

        function updateposition() {

            var date = new Date();
            var time = date.getMinutes() * 60 + date.getSeconds();
            Latitude = 31.76904;
            Longitude = 35.21633;
//      Latitude= 45*Math.sin((time-1800)*Math.PI/1800.0)
//      Longitude= (time/10)-180;
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