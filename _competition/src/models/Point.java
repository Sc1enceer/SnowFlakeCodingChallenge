package models;

public class Point implements Comparable<Point> {
    private double x;
    private double y;
    private double distance;
    private Algorithm algorithm;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void calculateDistance(){
        this.distance = algorithm.distance(this, new Point(50.937354, -1.397688));
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // getter and setter
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }



    // priority queue will use this value to sort



    public int compareTo(Point n) {
        if (this.getDistance() == n.getDistance()){
            return 0;
        } else if (this.getDistance() > n.getDistance()){
            return 1;
        } else {
            return -1;
        }
    }

}
