<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<table>
    <tr>
        <td>
            <div id="map-canvas"></div>
        </td>
        <td>
            <table id="text-canvas">
                <tr id="control">
                    <td>
                        <div id="speedLess">меньше</div>
                    </td>
                    <td>
                        <div> скорость</div>
                    </td>
                    <td>
                        <div id="speedMore">больше</div>
                    </td>
                </tr>
                <tr>
                    <td>

                        <div id="text" hidden>${text}</div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
<head>
    <title>Машина времени</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <LINK rel=stylesheet type="text/css" href=map.css>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/maplabel/src/maplabel-compiled.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="places.js"></script>
    <script src="place.js"></script>
    <script src="map.js"></script>
    <script src="globalBounds.js"></script>
    <script src="textScroll.js"></script>
    <script>
        google.maps.event.addDomListener(window, 'load', setPlaceSources(${places}));
        window.onload = move();
    </script>
</head>
</html>