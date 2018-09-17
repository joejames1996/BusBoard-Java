package training.busboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public class JsonParser
{
    public static void parseJsonString(String jsonString)
    {
        StopPoint[] busText = jsonParser(jsonString, StopPoint[].class);

        for (StopPoint bus : busText ) {
            System.out.println(bus);
        }
    }

    public static <T> T jsonParser(String jsonText, Class<T> classType ){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, (JsonDeserializer<String>) (jsonElement, type, jsonDeserializationContext) ->
                jsonElement.getAsString());
        Gson gs = gsonBuilder.create();
        return gs.fromJson(jsonText, classType);
    }
}
