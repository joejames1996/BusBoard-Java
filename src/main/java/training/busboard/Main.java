package training.busboard;

import java.util.Scanner;

public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main (String args[]) throws Exception
    {
        do
        {
            String input = scanner.nextLine().trim();
            if (input == null || input.isEmpty()) {continue;}
            ConsoleInput.readInput(input);
        } while (scanner.hasNextLine());

    }

}	
