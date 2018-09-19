package training.busboard;

import java.util.Date;

public class StopPoint implements Comparable<StopPoint>
{
    StopPoint[] stopPoints;
    final String id;
    final String lineName;
    final String stationName;
    final String destinationName;
    final String commonName;
    final Date   expectedArrival;
    final double lat;
    final double lon;
    double distanceFromPostcode;

    public StopPoint (String id, String lineName, String stationName, String destinationName, String commonName, Date expectedArrival, double lat, double lon
            , double distanceFromPostcode)
    {
        this.id = id;
        this.lineName = lineName;
        this.stationName = stationName;
        this.destinationName = destinationName;
        this.commonName = commonName;
        this.expectedArrival = expectedArrival;
        this.lat = lat;
        this.lon = lon;
        this.distanceFromPostcode = distanceFromPostcode;
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
        return String.format("%02d:%02d%02d", expectedArrival.getHours(), expectedArrival.getMinutes(), expectedArrival.getSeconds());
    }

}
