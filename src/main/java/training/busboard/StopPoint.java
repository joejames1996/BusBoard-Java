package training.busboard;

import java.util.Date;

public class StopPoint
{
    private int id;
    private int operationType;
    private String vehicleId;
    private String naptanId;
    private String stationName;
    private String lineId;
    private String lineName;
    private String platformName;
    private String direction;
    private int bearing;
    private String destinationNaptanId;
    private String destinationName;
    private Date timestamp;
    private int timeToStation;
    private String currentLocation;
    private String towards;
    private String expectedArrival;
    private Date timeToLive;
    private String modeName;

    public String toString(){
        return "ID: " + id +
                "\toperationType: " + operationType +
                "\tvehicleId: " + vehicleId +
                "\tnaptanId: " + naptanId +
                "\tstationName: " + stationName +
                "\tlineId: " + lineId +
                "\tlineName: " + lineName +
                "\tplatformName: " + platformName +
                "\tdirection: " + direction +
                "\tbearing: " + bearing +
                "\tdestinationNaptanId: " + destinationNaptanId +
                "\tdestinationName: " + destinationName +
                "\ttimestamp: " + timestamp +
                "\ttimeToStation:" + timeToStation +
                "\tcurrentLocation:" + currentLocation +
                "\ttowards: " + towards +
                "\texpectedArrival: " + expectedArrival +
                "\ttimeToLive: " + timeToLive +
                "\tmodeName: " + modeName;
    }

}
