package models;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.*;

public class Parser {

    static String filePath = "/Users/gongweishi/documents/github/snowflake/_competition/notams-geojson.json";
    private Obstacle obstacleModel;
    private Property propertyModel;
    private Geometry geometryModel;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static double MINIMUMHEIGHT = 0.0;
    private static double MAXIMUMHEIGHT = 99999.0;

    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    public Parser(){
        //initialise();
    }


    public   void initialise(){
        JSONParser parser = new JSONParser();
        try {
            // read file
            JSONObject object = (JSONObject) parser.parse(new FileReader(filePath));
            // feature is the whole array
            JSONArray array = (JSONArray) object.get("features");
            // iterate through each element
            for( int i = 0; i< array.size(); i++){
                JSONObject element = (JSONObject) array.get(i);
                JSONObject geometry = (JSONObject) element.get("geometry");
                JSONObject property = (JSONObject) element.get("properties");

                // parse the features
                String description = (String) property.get("text");
                String startTime = (String) property.get("start_time");
                String endTime = (String) property.get("end_time");
                String lowerAltitude = (String) property.get("lower_altitude");
                String upperAltitude = (String) property.get("upper_altitude");
                // parse geometry
                String type = (String) geometry.get("type");
                JSONArray coordinateArray =  (JSONArray) geometry.get("coordinates");
                ArrayList<Point> pointList = new ArrayList<Point>();
                // generate points
                for (Object o : coordinateArray){
                    JSONArray pointArray = (JSONArray) o;
                    for (int m = 0; m < pointArray.size(); m++){
                        JSONArray singleArray = (JSONArray) pointArray.get(m);
                        Point point = new Point(Double.parseDouble(singleArray.get(0).toString()),
                                                Double.parseDouble( singleArray.get(1).toString()));
                        pointList.add(point);
                    }
                }

                propertyModel = generateProperties(startTime, endTime, lowerAltitude, upperAltitude, description);
                geometryModel = generateGeometry(type ,pointList);
                obstacleModel = new Obstacle(propertyModel, geometryModel);
                obstacles.add(obstacleModel);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private  Property generateProperties(String startTime, String endTime, String lowerAltitude, String upperAltitude, String description){


        try {
            Date start = formatter.parse(startTime);

            Date end;
            if (endTime.equals("PERM")){
                end = formatter.parse("9999-99-99T99:99:99Z");
            } else {
                end = formatter.parse(endTime);
            }

            double lowerAlt = 0.0;
            if (lowerAltitude.equals("GND")){
                lowerAlt = MINIMUMHEIGHT;
            } else {
                if (lowerAltitude.contains("FT")){
                    lowerAlt = Double.parseDouble(lowerAltitude.replace(" FT", "").toString());
                } else  { // it is meter
                    lowerAlt = 3.28084 * Double.parseDouble(lowerAltitude.replace(" M", "").toString());
                }
            }


            double upperAlt = 0.0;

            if (upperAltitude.equals("UNL")){
                upperAlt = MAXIMUMHEIGHT;
            } else {
                if (upperAltitude.contains("FT")){
                    upperAlt = Double.parseDouble(upperAltitude.replace(" FT", "").toString());
                } else  { // it is meter
                    upperAlt = 3.28084 * Double.parseDouble(upperAltitude.replace(" M", "").toString());
                }
            }

            return new Property(start, end, lowerAlt, upperAlt, description);


        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return null;


    }

    private Geometry generateGeometry(String type, ArrayList<Point> points) {
        return new Geometry(type, points);
    }



    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public DateFormat getFormatter() {
        return formatter;
    }
}
