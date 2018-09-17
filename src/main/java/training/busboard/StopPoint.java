package training.busboard;

import java.util.Date;

public class StopPoint implements Comparable<StopPoint>
{
    public StopPoint[] stopPoints;

    private String id;
    private String lineName;
    private String destinationName;
    private String commonName;
    private Date expectedArrival;

    public String toString ()
    {
        return "expectedArrival: " + expectedArrival +
               "\tlineName: " + lineName +
               "\tdestinationName: " + destinationName +
               "\tID: " + id +
                "\tcommonName: " + commonName;
    }

    public int compareTo (StopPoint o)
    {
        return this.expectedArrival.compareTo(o.expectedArrival);
    }

}
