<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<div id="map-canvas"></div>
<div id="text-canvas">
    <div id="text">${text}</div>
</div>

</body>
<head>
    <title>Машина времени</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <LINK rel=stylesheet type="text/css" href=map.css>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/maplabel/src/maplabel-compiled.js"></script>
    <script src="places.js"></script>
    <script src="place.js"></script>
    <script src="map.js"></script>
    <script src="textScroll.js"></script>
    <script>
        google.maps.event.addDomListener(window, 'load', setPlaceSources(${places}));
        window.onload = move();
    </script>
</head>
</html>