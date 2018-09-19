package training.busboard;

import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleInput extends Thread
{
    StopPoint[] stopPointArray;
    String      url;

    public ConsoleInput (String url) { this.url = url; }

    public static StopPoint[] readInput (String lineOfText) throws Exception
    {
        InputType responseType = getInputType(lineOfText);

        // Stop Code process
        if (responseType == InputType.STOP_CODE) { return stopPointPrinting(lineOfText); }

        // Postcode process
        else if (responseType == InputType.POST_CODE)
        {
            String   jsonResponse = Request.sendPostCodeRequest(lineOfText);
            Postcode postcode     = JsonParser.jsonParser(jsonResponse, Postcode.class);

            // Get postcode
            if (postcode != null)
            {
                jsonResponse = Request.sendLatLonRequest(postcode.result.latitude, postcode.result.longitude);
                List<StopPoint>              stopPointList        = Arrays.asList(JsonParser.jsonParser(jsonResponse, StopPoint.class).stopPoints);
                SortedMap<Double, StopPoint> stopPointDistanceMap = new TreeMap<>();

                // Distance between current and found stopoints
                for (int stopPointIndex = 0; stopPointIndex < stopPointList.size(); stopPointIndex++)
                {
                    stopPointList.get(stopPointIndex).distanceFromPostcode = StopsFromLatLong.latLongDistance(postcode.result.latitude,
                                                                                                              postcode.result.longitude,
                                                                                                              stopPointList.get(stopPointIndex).lat,
                                                                                                              stopPointList.get(stopPointIndex).lon);
                    stopPointDistanceMap.put(stopPointList.get(stopPointIndex).distanceFromPostcode, stopPointList.get(stopPointIndex));
                }

                // Thread Data
                int             threadTotalIndex   = 0;
                List<StopPoint> spArrayList        = new ArrayList<>();
                ConsoleInput[]  requestThreadArray = new ConsoleInput[stopPointDistanceMap.size()];

                // Create Threads for stop code process
                for (double spDouble : stopPointDistanceMap.keySet())
                {
                    requestThreadArray[threadTotalIndex] = new ConsoleInput(stopPointDistanceMap.get(spDouble).id);
                    requestThreadArray[threadTotalIndex++].start();
                }
                // Join threads back
                for (int threadIndex = 0; threadIndex < threadTotalIndex; threadIndex++)
                {
                    requestThreadArray[threadIndex].join();
                    spArrayList.addAll(Arrays.asList(requestThreadArray[threadIndex].stopPointArray));
                }

                System.out.printf("%s Sorting and returning\n", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));

                Collections.sort(spArrayList);
                StopPoint[] sp = (StopPoint[]) spArrayList.toArray();
                for (int i = 0; i < sp.length; i++)
                {
                    System.out.printf("%s\n", sp[i]);
                }
                return sp;
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
        String      response       = Request.sendStopPointRequest(lineOfText);
        StopPoint[] stopPointArray = JsonParser.jsonParser(response, StopPoint[].class);

        for (int i = 0; i < stopPointArray.length && i < 5; i++)
        {
            System.out.printf("%s\n\n", stopPointArray[i]);
        }

        return stopPointArray;
    }

    public void run () { try {stopPointArray = stopPointPrinting(url);} catch (Exception e) {} }

}
