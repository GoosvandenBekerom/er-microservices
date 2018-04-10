package com.s63d.location.event;

import java.io.Serializable;

public class LocationEvent implements Serializable {

    public LocationEvent() {}

        public LocationEvent(Long tripId, Double lat, Double lon) {
            this.tripId = tripId;
            this.lat = lat;
            this.lon = lon;
        }

        private Long id;
        private Long tripId;
        private Double lat;
        private Double lon;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getTripId() {
            return tripId;
        }

        public void setTripId(Long tripId) {
            this.tripId = tripId;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    @Override
    public String toString() {
        return "LocationEvent{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

}
