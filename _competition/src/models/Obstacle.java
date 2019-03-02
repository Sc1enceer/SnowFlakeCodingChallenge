package models;

public class Obstacle {
    private Property property;
    private Geometry geometry;

    public Obstacle(Property property, Geometry geometry) {
        this.property = property;
        this.geometry = geometry;
    }
    // getter and setters

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
