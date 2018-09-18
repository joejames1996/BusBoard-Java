package training.busboard;

import java.util.*;

public class ConsoleInput
{


    public static void readInput (String lineOfText)
    {
            InputType responseType = getInputType(lineOfText);
            if (responseType == InputType.STOP_CODE) { stopPointPrinting(lineOfText); }
            else if (responseType == InputType.POST_CODE)
            {
                String   response = Request.sendRequest(lineOfText, InputType.POST_CODE);
                Postcode json     = JsonParser.jsonParser(response, Postcode.class);
                if (json != null)
                {
                    String latLong = "&lat=" + json.result.latitude + "&lon=" + json.result.longitude;

                    String stuff = Request.sendRequest(latLong, InputType.LAT_LONG);

                    StopPoint                    sp    = JsonParser.jsonParser(stuff, StopPoint.class);
                    SortedMap<Double, StopPoint> spMap = new TreeMap<>();

                    for (int i = 0; i < sp.stopPoints.length; i++)
                    {
                        //System.out.println(sp.stopPoints[i]);
                        sp.stopPoints[i].distanceFromPostcode = StopsFromLatLong.latLongDistance(json.result.latitude, json.result.longitude,
                                                                                                 sp.stopPoints[i].lat, sp.stopPoints[i].lon);
                        //System.out.println(sp.stopPoints[i].distanceFromPostcode);

                        spMap.put(sp.stopPoints[i].distanceFromPostcode, sp.stopPoints[i]);
                    }

                    System.out.println(spMap.get(spMap.keySet().toArray()[0]).distanceFromPostcode);
                    System.out.println(spMap.get(spMap.keySet().toArray()[1]).distanceFromPostcode);

                    stopPointPrinting(spMap.get(spMap.keySet().toArray()[0]).id);
                    stopPointPrinting(spMap.get(spMap.keySet().toArray()[1]).id);

                    //spMap.values().toArray()
                }
            }
    }

    private static InputType getInputType (String input)
    {
        if (input.matches("^[^\\d].*"))
        {
            return InputType.POST_CODE;
        }
        else { return InputType.STOP_CODE; }
    }

    private static void stopPointPrinting (String lineOfText)
    {
        String      response = Request.sendRequest(lineOfText, InputType.STOP_CODE);
        StopPoint[] json     = JsonParser.jsonParser(response, StopPoint[].class);
        for (int i = 0; i < json.length && i < 5; i++)
        {
            System.out.println(json[i]);
        }
    }
}
