package training.busboard;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class StopPoint implements Comparable<StopPoint>
{
    public StopPoint[] stopPoints;

    public final String id;
    public final String lineName;
    public final String stationName;
    public final String destinationName;
    public final String commonName;
    public final Date   expectedArrival;
    public final double lat;
    public final double lon;
    public       double distanceFromPostcode;

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
        return "expectedArrival: " + expectedArrival + "\nlineName: " + lineName + "\ndestinationName: " + destinationName + "\nID: " + id + "\ncommonName: " +
               commonName + "\nlat: " + lat + "\nlon: " + lon + "\ndistanceFromPostcode: " + distanceFromPostcode;
    }

    public int compareTo (StopPoint o)
    {
        return this.expectedArrival.compareTo(o.expectedArrival);
    }

    public String getArrival ()
    {
        return String.format("%02d", expectedArrival.getHours()) + ":" + String.format("%02d", expectedArrival.getMinutes()) + ":" +
               String.format("%02d", expectedArrival.getSeconds());
    }

}
