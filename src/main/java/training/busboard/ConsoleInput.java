package training.busboard;

import javafx.scene.paint.Stop;

import java.util.*;

public class ConsoleInput extends Thread
{
    StopPoint[] stopPointArray;
    String      url;

    public ConsoleInput (String url) { this.url = url; }

    public static StopPoint[] readInput (String lineOfText) throws Exception
    {
        InputType responseType = getInputType(lineOfText);
        if (responseType == InputType.STOP_CODE)
        {
            StopPoint[] json = stopPointPrinting(lineOfText);
            for (int i = 0; i < json.length && i < 5; i++)
            {
                System.out.println(json[i]);
            }
            return json;
        }
        else if (responseType == InputType.POST_CODE)
        {
            String   response = Request.sendRequest(lineOfText, InputType.POST_CODE);
            Postcode json     = JsonParser.jsonParser(response, Postcode.class);
            if (json != null)
            {
                String latLong = "&lat=" + json.result.latitude + "&lon=" + json.result.longitude;
                String stuff   = Request.sendRequest(latLong, InputType.LAT_LONG);

                StopPoint                    sp    = JsonParser.jsonParser(stuff, StopPoint.class);
                SortedMap<Double, StopPoint> spMap = new TreeMap<>();

                for (int i = 0; i < sp.stopPoints.length; i++)
                {
                    sp.stopPoints[i].distanceFromPostcode = StopsFromLatLong.latLongDistance(json.result.latitude, json.result.longitude, sp.stopPoints[i].lat,
                                                                                             sp.stopPoints[i].lon);
                    spMap.put(sp.stopPoints[i].distanceFromPostcode, sp.stopPoints[i]);
                }

                List<StopPoint> spArrayList = new ArrayList<>();

                ConsoleInput[] threaded = new ConsoleInput[spMap.size()];

                int threadTotalIndex = 0;
                for (double spDouble : spMap.keySet())
                {
                    threaded[threadTotalIndex] = new ConsoleInput(spMap.get(spDouble).id);
                    threaded[threadTotalIndex++].start();
                }
                for (int threadIndex = 0; threadIndex < threadTotalIndex; threadIndex++)
                { spArrayList.addAll(Arrays.asList(threaded[threadIndex].stopPointArray)); }

                Collections.sort(spArrayList);
                for (int index = 0; index < spArrayList.size(); index++)
                {
                    System.out.println(spArrayList.get(index));
                }

                StopPoint[] spArray = new StopPoint[spArrayList.size()];
                spArrayList.toArray(spArray);
                return spArray;
            }
        }

        return null;
    }

    private static InputType getInputType (String input)
    {
        if (input.matches("^[^\\d].*"))
        {
            return InputType.POST_CODE;
        }
        else { return InputType.STOP_CODE; }
    }

    private static StopPoint[] stopPointPrinting (String lineOfText) throws Exception
    {
        String      response = Request.sendRequest(lineOfText, InputType.STOP_CODE);
        StopPoint[] json     = JsonParser.jsonParser(response, StopPoint[].class);
        return json;
    }

    public void run () { try {stopPointArray = stopPointPrinting(url);} catch (Exception e) {} }

}
