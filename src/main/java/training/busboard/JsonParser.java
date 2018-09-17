package training.busboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Arrays;

public class JsonParser
{
    private static int maxWanted = 5;

    public static void parseJsonString (String jsonString)
    {
        StopPoint[] busText = jsonParser(jsonString, StopPoint[].class);
        int maxIndex = busText.length > maxWanted ? maxWanted : busText.length;

        Arrays.sort(busText);
        for (int busIndex = 0; busIndex < maxIndex; busIndex ++)
        {
            System.out.println(busText[busIndex] );
        }
    }

    public static <T> T jsonParser (String jsonText, Class<T> classType)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, (JsonDeserializer<String>) (jsonElement, type, jsonDeserializationContext)->jsonElement.getAsString());
        Gson gs = gsonBuilder.create();
        return gs.fromJson(jsonText, classType);
    }
}
