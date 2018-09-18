package training.busboard;

import java.util.Scanner;

public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main (String args[])
    {
        do
        {
            String input = scanner.nextLine();
            if (input == null) {continue;}
            ConsoleInput.readInput(input);
        } while (scanner.hasNextLine());

    }

}	
