import models.Algorithm;
import models.Drone;
import models.Parser;
import models.Point;

import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] agrs){
        // declare 2 drone objects
        Drone drone2170R = new Drone(2500 * 3.28084);
        Drone drone2195 = new Drone(4500 * 3.28084);

        Parser parser = new Parser();
        parser.initialise();

        //System.out.println(parser.getObstacles().get(0).getProperty().getSatrtTime());
        Algorithm algorithm = new Algorithm(new Point(50.9026744,-1.4060164), new Point(50.937354, -1.397688), parser.getObstacles(), drone2195);

    }
}
