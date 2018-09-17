package training.busboard;

public class Main
{
    public final static InputType inputType = InputType.POST_CODE;

    public static void main (String args[])
    {
        Request.getRequestUrl();
        printFirstLine();
        ConsoleInput.readInput();
    }

    private static void printFirstLine()
    {
        switch(inputType)
        {
            case STOP_CODE:
                System.out.println("Enter a bus stop code:");
                break;
            case POST_CODE:
                System.out.println("Enter a postcode:");
        }
    }
}	
