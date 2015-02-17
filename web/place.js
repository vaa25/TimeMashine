/**
 * Created by vaa25 on 12.02.2015.
 */
function Place(placeSource) {
    this.title = placeSource.title;
    this.latLons = getLatLons(placeSource.bound);
    this.center = getLatLng(placeSource.center);
    this.latLngBounds = createLatLngBound(this);
    this.polygon = createPolygon(this.latLons);

    this.mark = function () {
        this.polygon.setOptions({fillOpacity: 0.35});
    };

    this.unmark = function () {
        this.polygon.setOptions({fillOpacity: 0.1});
    };

    this.draw = function () {
        drawMarker(this);
        drawBound(this);
    };


    function createPolygon(bound) {
        return new google.maps.Polygon({
            path: bound,
            strokeColor: "#FF0000",
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: "#FF0000",
            fillOpacity: 0.10
        });
    }

    function drawBound(me) {
        me.polygon.setOptions({map: map});
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

    function createLatLngBound(me) {
        var latLngBound = new google.maps.LatLngBounds(me.center, me.center);
        for (var index in me.latLons) {
            if (me.latLons.hasOwnProperty(index)) {
                latLngBound.extend(me.latLons[index]);
            }
        }
        return latLngBound;
    }
}
