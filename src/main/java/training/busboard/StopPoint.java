package training.busboard;

import java.util.Date;

public class StopPoint implements Comparable<StopPoint>
{
    public       StopPoint[] stopPoints;
    public final String      id;
    public final String      lineName;
    public final String      stationName;
    public final String      destinationName;
    public final String      commonName;
    public final Date        expectedArrival;
    public final double      lat;
    public final double      lon;
    public       double      distanceFromPostcode;

    public StopPoint (String id, String lineName, String stationName, String destinationName, String commonName, Date expectedArrival, double lat, double lon)
    {
        this.id = id;
        this.lineName = lineName;
        this.stationName = stationName;
        this.destinationName = destinationName;
        this.commonName = commonName;
        this.expectedArrival = expectedArrival;
        this.lat = lat;
        this.lon = lon;
    }

    public String toString ()
    {
        return String.format("Expected Arrival: %s\n" + "Line Name: %s\n" + "Destination Name: %s\n" + "ID: %s\n" + "Common Name: %s\n" + "Latitude: %s\n" +
                             "Longitude: %s\n" + "Distance: %s\n", expectedArrival, lineName, destinationName, id, commonName, lat, lon, distanceFromPostcode);
    }

    public int compareTo (StopPoint o)
    {
        return this.expectedArrival.compareTo(o.expectedArrival);
    }

    public String getArrival ()
    {
        return String.format("%02d:%02d:%02d", expectedArrival.getHours(), expectedArrival.getMinutes(), expectedArrival.getSeconds());
    }

}
