package training.busboard;

import java.util.Scanner;

public class ConsoleInput
{
    public static void readInput()
    {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine())
        {
            String lineOfText = scanner.nextLine();

            String response = Request.sendRequest(lineOfText);
            JsonParser.parseJsonString(response);
        }
    }
}
