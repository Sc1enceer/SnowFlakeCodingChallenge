package models;

import java.util.Date;

public class Property {
    private Date satrtTime;
    private Date endTime;
    private double lower_altitude;
    private double upper_altitude;
    private String description;

    public Property(Date satrtTime, Date endTime, double lower_altitude, double upper_altitude, String description) {
        this.satrtTime = satrtTime;
        this.endTime = endTime;
        this.lower_altitude = lower_altitude;
        this.upper_altitude = upper_altitude;
        this.description = description;
    }

    public Date getSatrtTime() {
        return satrtTime;
    }

    public void setSatrtTime(Date satrtTime) {
        this.satrtTime = satrtTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getLower_altitude() {
        return lower_altitude;
    }

    public void setLower_altitude(double lower_altitude) {
        this.lower_altitude = lower_altitude;
    }

    public double getUpper_altitude() {
        return upper_altitude;
    }

    public void setUpper_altitude(double upper_altitude) {
        this.upper_altitude = upper_altitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
