package training.busboard;

import java.util.Date;

public class StopPoint implements Comparable<StopPoint>
{
    private String lineName;
    private String destinationName;
    private Date expectedArrival;

    public String toString ()
    {
        return "expectedArrival: " + expectedArrival +
               "\tlineName: " + lineName +
               "\tdestinationName: " + destinationName;
    }

    @Override
    public int compareTo (StopPoint o)
    {
        return this.expectedArrival.compareTo(o.expectedArrival);
    }

}
