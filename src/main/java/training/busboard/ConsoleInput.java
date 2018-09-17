package training.busboard;

import java.util.Scanner;

public class ConsoleInput
{
    public static void readInput ()
    {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine())
        {
            System.out.println("Please input a postcode or stopcode: \n");
            String lineOfText = scanner.nextLine();
            if (lineOfText.isEmpty()) continue;

            String response = Request.sendRequest(lineOfText);
            /*
            StopPoint[] json     = JsonParser.jsonParser(response, StopPoint[].class);
            for (int i = 0; i < json.length && i < 5; i++)
            {
                System.out.println(json[i]);
            }
            //*/
            //*
            Postcode json = JsonParser.jsonParser(response, Postcode.class);

            if (json != null)
            {
                String latLong = "&lat=" + json.result.latitude + "&lon=" + json.result.longitude;
                Request.requestTFL(latLong);

                StopPoint sp = (StopPoint) JsonParser.jsonParser(Request.requestTFL(latLong), StopPoint.class);
                for (int i = 0; i < sp.stopPoints.length; i++)
                { System.out.println(sp.stopPoints[i]); }
            }
            //*/
        }
    }
}
