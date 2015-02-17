/**
 * Created by vaa25 on 15.02.2015.
 */
var viewport;
function Viewport() {
    this.latLngBounds;
    this.add = function (bound, options) {
        if (this.latLngBounds == undefined || options == 'center') {
            this.latLngBounds = new google.maps.LatLngBounds(bound.getSouthWest(), bound.getNorthEast());
        } else if (options == 'addBound') {
            this.latLngBounds.union(bound);
        }
    };
    this.getLatLngBounds = function () {
        return this.latLngBounds;
    };
    this.getCenter = function () {
        return this.latLngBounds.getCenter();
    };
    this.getPreferZoom = function () {
        var width = this.latLngBounds.getNorthEast().lat() - this.latLngBounds.getSouthWest().lat();
        var height = this.latLngBounds.getNorthEast().lng() - this.latLngBounds.getSouthWest().lng();
        return Math.round(14 - Math.log(Math.max(width, height) / 0.025) / Math.log(2));
    };

}