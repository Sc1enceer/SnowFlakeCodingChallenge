package models;

import java.util.ArrayList;

public class Geometry {
    private String type;
    private ArrayList<Point> points;


    public Geometry(String type, ArrayList<Point> points) {
        this.type = type;
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
}
