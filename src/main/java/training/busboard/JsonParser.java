package training.busboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public class JsonParser
{
    private static int maxWanted = 5;

    public static Object parseJsonString (String jsonString, InputType inputType)
    {
        if (inputType == InputType.STOP_CODE) { return jsonParser(jsonString, StopPoint[].class); }
        else if (inputType == InputType.POST_CODE) { return jsonParser(jsonString, Postcode.class); }
        else if (inputType == InputType.LAT_LONG) { return jsonParser(jsonString, StopPoint.class); }
        return null;

    }

    public static <T> T jsonParser (String jsonText, Class<T> classType)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, (JsonDeserializer<String>) (jsonElement, type, jsonDeserializationContext)->jsonElement.getAsString());
        Gson gs = gsonBuilder.create();
        return gs.fromJson(jsonText, classType);
    }
}
