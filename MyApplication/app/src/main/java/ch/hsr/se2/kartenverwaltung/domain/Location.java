package ch.hsr.se2.kartenverwaltung.domain;

/**
 * Created by roberto on 29.03.15.
 */
public class Location {
    private int locationId;
    private Position position;
    private int numberOfVisits;

    public Location(final Position position, final int numberOfVisits) {
        this.position = position;
        this.numberOfVisits = numberOfVisits;
    }

    public int getLocationId() {
        return this.locationId;
    }

    public void setLocationId(final int locationId) {
        this.locationId = locationId;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getNumberOfVisits() {
        return this.numberOfVisits;
    }
}
