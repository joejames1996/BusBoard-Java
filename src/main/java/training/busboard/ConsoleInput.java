package training.busboard;

import java.util.Scanner;

public class ConsoleInput
{
    public static void readInput ()
    {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine())
        {
            String lineOfText = scanner.nextLine();

            String   response = Request.sendRequest(lineOfText);
            Postcode json     = (Postcode) JsonParser.parseJsonString(response);
            if (json != null)
            {
                String latLong = "&lat=" + json.result.latitude + "&lon=" + json.result.longitude;
                Request.requestTFL(latLong);

                StopPoint sp = (StopPoint) JsonParser.stopCodeJsonP(Request.requestTFL(latLong));
                for (int i = 0; i < sp.stopPoints.length; i++)
                { System.out.println(sp.stopPoints[i]); }

            }
        }
    }
}
