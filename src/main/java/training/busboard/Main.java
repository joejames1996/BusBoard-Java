package training.busboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Main
{
    //private static String request = "https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals?app_id=ad5c6319&app_key=5df3db201bb778d8ba63676ad04a21e7";

    public static void main(String args[])
    {
        System.out.println("Enter a bus stop code:");
        ConsoleInput.readInput();

        String jsonText;
        StopPoint[] busText;

        //Client client = ClientBuilder.newBuilder().register(StopPoint.class).register(JacksonFeature.class).build();
        //System.out.println(jsonText = client.target(request).request().get(String.class));

        //busText = jsonParser(jsonText, StopPoint[].class);

        /*for (StopPoint bus : busText ) {
            System.out.println(bus);
        }*/
    }

    public static <T> T jsonParser(String jsonText, Class<T> classType ){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, (JsonDeserializer<String>) (jsonElement, type, jsonDeserializationContext) ->
                jsonElement.getAsString());
        Gson gs = gsonBuilder.create();
        return gs.fromJson(jsonText, classType);
    }

}	
