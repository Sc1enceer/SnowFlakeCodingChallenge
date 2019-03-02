package models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

public class Algorithm {

    /*
        SO17 1BJ (50.937354, -1.397688) at 12:00

        SO15 1DE (50.9026744,-1.4060164)
     */


    // 2 params, x is soton, y is pizza
    // this function will return the hupothnuse distance between 2 coordinates in meter

    Parser parser = new Parser();

    public Algorithm(Point x, Point ansnwer, ArrayList<Obstacle> obstacles, Drone drone) {
        aStar(x, ansnwer, obstacles, drone);
    }

    public  double distance(Point x, Point y){
        double length = Math.abs(y.getX() - x.getX());
        double height = Math.abs(y.getY() - x.getY());

        double hypotenuse = Math.sqrt(Math.pow(length, 2) + Math.pow(height, 2));

        return degreeToMeter(hypotenuse);
    }


    private double degreeToMeter(double value){
        return (value / 0.00000001) * 0.001;
    }


    private void aStar(Point x, Point answer, ArrayList<Obstacle> obstacles, Drone drone){
        // get all obstacle points
        ArrayList<Point> allObstaclePoints = new ArrayList<Point>();

        for (int i = 0; i< obstacles.size(); i++){
            // only obstacle higher than drone's flying height will be considered
            if (obstacles.get(i).getProperty().getUpper_altitude() > drone.getSERVICECEILING()){

                try {
                    Date today = parser.getFormatter().parse("2019-03-02T12:00:00Z");

                    // if obstacle occurs during the transportation
                    if ((obstacles.get(i).getProperty().getSatrtTime().compareTo(today) <= 0)
                            && obstacles.get(i).getProperty().getEndTime().compareTo(today)>=0) {
                        allObstaclePoints.addAll(obstacles.get(i).getGeometry().getPoints());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //if (obstacles.get(i).getProperty().getSatrtTime() > )

        }

        int nodesExpanded = 0;
        Queue<Point> fringe = new PriorityQueue<Point>();
        fringe.add(x);
        ArrayList<Point> solutionStep = new ArrayList<Point>();
        while (!fringe.isEmpty()){
            ArrayList<Point> successors = new ArrayList<Point>();
            System.out.println(fringe.size());
            Point current = fringe.poll();
            //System.out.println(current.getX());
            solutionStep.add(current);




            // if the destination is within the flying distance, we found the solution
            if ((degreeToMeter(Math.abs(current.getX() - answer.getX())) <= drone.getMAXSPEED() * 10)
                    &&
                    (degreeToMeter(Math.abs( current.getY() - answer.getY())) <= (drone.getMAXSPEED()*10))){

                System.out.println("Answer is found with " + nodesExpanded + " points explored");
                for (int i = 0; i < solutionStep.size(); i++){
                    System.out.println("Step " + i + " X : " + solutionStep.get(i).getX() + " Y : " + solutionStep.get(i).getY());
                }
                break;

            }



            nodesExpanded++;
            double tempValue = meterToDegree(drone) * 10; // every 10 second
            // up
            successors.add(new Point(current.getX() , current.getY() + tempValue));
            // down
            successors.add(new Point(current.getX() , current.getY() - tempValue));
            // left
            successors.add(new Point(current.getX() - tempValue , current.getY()));
            // right
            successors.add(new Point(current.getX() + tempValue, current.getY()));

            for (int i = 0; i< successors.size(); i++){
                if (!allObstaclePoints.contains(successors.get(i))){
                    //successors.get(i).calculateDistance();
                    fringe.add(successors.get(i));
                }
            }
        }
    }


    public double meterToDegree( Drone drone){
        return (drone.getMAXSPEED() / 0.001) * .00000001;
    }
}








