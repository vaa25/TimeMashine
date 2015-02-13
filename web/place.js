/**
 * Created by vaa25 on 12.02.2015.
 */
function Place(place) {
    this.title = place.title;
    this.bound = getLatLons(place.bound);
    this.center = getLatLng(place.center);
    this.latLngBound = getLatLngBound(this);
    this.draw = function () {
        drawMarker(this);
        drawBound(this.bound);
    };
    this.show = function () {
        console.log(this.latLngBound);
        map.panToBounds(this.latLngBound);
    };

    function drawBound(bound) {
        return new google.maps.Polygon({
            path: bound,
            strokeColor: "#FF0000",
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: "#FF0000",
            fillOpacity: 0.35,
            map: map
        });
    }

    function drawMarker(me) {
        var marker = new google.maps.Marker;
        var mapLabel = getMapLabel(me);
        marker.bindTo('map', mapLabel);
        marker.bindTo('position', mapLabel);
        marker.setDraggable(false);
        return marker;
    }

    function getMapLabel(me) {
        return new MapLabel({
            text: me.title,
            position: me.center,
            map: map,
            fontSize: 16,
            align: 'right'
        });
    }

    function getLatLngBound(me) {
        var latLngBound = new google.maps.LatLngBounds(me.center, me.center);
        var count = 0;
        for (var index in me.bound) {
            if (me.bound.hasOwnProperty(index)) {
                latLngBound.extend(me.bound[index]);
                console.log(count++);
            }
        }
        return latLngBound;
    }
}
