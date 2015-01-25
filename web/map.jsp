<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<div id="map-canvas"></div>
</body>
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
    <script src="places.js"></script>
    <script>
        google.maps.event.addDomListener(window, 'load', initialize(${places}));
    </script>
</head>
</html>