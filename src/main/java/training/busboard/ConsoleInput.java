package training.busboard;

import java.util.Scanner;

public class ConsoleInput
{
    public static void readInput ()
    {
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("Please input a postcode or stopcode:");
            String lineOfText = scanner.nextLine().trim();
            if (lineOfText.isEmpty()) continue;

            InputType responseType = getInputType(lineOfText);
            if (responseType == InputType.STOP_CODE)
            {
                String      response = Request.sendRequest(lineOfText, InputType.STOP_CODE);
                StopPoint[] json     = JsonParser.jsonParser(response, StopPoint[].class);
                for (int i = 0; i < json.length && i < 5; i++)
                {
                    System.out.println(json[i]);
                }
            }
            else if (responseType == InputType.POST_CODE)
            {
                String   response = Request.sendRequest(lineOfText, InputType.POST_CODE);
                Postcode json     = JsonParser.jsonParser(response, Postcode.class);
                if (json != null)
                {
                    String latLong = "&lat=" + json.result.latitude + "&lon=" + json.result.longitude;

                    String stuff = Request.sendRequest(latLong, InputType.LAT_LONG);

                    StopPoint sp = JsonParser.jsonParser(stuff, StopPoint.class);
                    for (int i = 0; i < sp.stopPoints.length; i++)
                    { System.out.println(sp.stopPoints[i]); }
                }
            }

        } while (scanner.hasNextLine());
    }

    private static InputType getInputType(String input)
    {
        if(input.matches("^[^\\d].*"))
        {
            return InputType.POST_CODE;
        }
        else return InputType.STOP_CODE;
    }
}
