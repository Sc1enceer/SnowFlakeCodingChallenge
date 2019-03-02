package models;

public class Drone {
    private int MAXSPEED = 65000/(60*60);
    private double ASCENTSPEED = 5 * 3.28084;
    private double DESCENTSPEED = 3;
    private double SERVICECEILING;

    public Drone(double SERVICECEILING) {
        this.SERVICECEILING = SERVICECEILING;
    }




    // getter and setters
    public int getMAXSPEED() {
        return MAXSPEED;
    }

    public void setMAXSPEED(int MAXSPEED) {
        this.MAXSPEED = MAXSPEED;
    }

    public double getASCENTSPEED() {
        return ASCENTSPEED;
    }

    public void setASCENTSPEED(int ASCENTSPEED) {
        this.ASCENTSPEED = ASCENTSPEED;
    }

    public double getDESCENTSPEED() {
        return DESCENTSPEED;
    }

    public void setDESCENTSPEED(int DESCENTSPEED) {
        this.DESCENTSPEED = DESCENTSPEED;
    }

    public double getSERVICECEILING() {
        return SERVICECEILING;
    }

    public void setSERVICECEILING(int SERVICECEILING) {
        this.SERVICECEILING = SERVICECEILING;
    }
}
