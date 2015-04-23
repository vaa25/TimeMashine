<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Машина времени</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <LINK rel=stylesheet type="text/css" href=css/map.css>
</head>
<body>
<div class="content">
    <div class="left">карта</div>
    <div class="right">
        <div class="top">
            <button>меньше</button>
            <span>скорость</span>
            <button>больше</button>
        </div>
        <div class="bottom">
            <div class="wrap" hidden>
                ${text}
            </div>
        </div>
    </div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/maplabel/src/maplabel-compiled.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="javascript/places.js"></script>
<script src="javascript/place.js"></script>
<script src="javascript/map.js"></script>
<script src="javascript/globalBounds.js"></script>
<script src="javascript/textScroll.js"></script>
<script>
    <%--google.maps.event.addDomListener(window, 'load', setPlaceSources(${places}));--%>
    $(document).ready(function () {
        setPlaceSources(${places});
        move();
    })
</script>
</body>
</html>