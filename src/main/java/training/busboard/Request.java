package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Request
{
    private        String naptanId;
    private static String requestPrefix = "";
    private static String requestSuffix = "";
    private static String requestLatLon = "https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&radius=200";

    public Request (String naptanId)
    {
        this.naptanId = naptanId;
    }

    public static void getRequestUrl ()
    {
        switch (Main.inputType)
        {
            case STOP_CODE:
                requestPrefix = "https://api.tfl.gov.uk/StopPoint/";
                requestSuffix = "/Arrivals?app_id=ad5c6319&app_key=5df3db201bb778d8ba63676ad04a21e7";
                break;
            case POST_CODE:
                requestPrefix = "http://api.postcodes.io/postcodes/";
                requestSuffix = "";
                break;
        }
    }

    private static String buildRequest (String naptanId)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestPrefix);
        stringBuilder.append(naptanId);
        stringBuilder.append(requestSuffix);
        return stringBuilder.toString();
    }

    public static String requestTFL (String naptanId)
    {
        Request request       = new Request(naptanId);
        String  requestString = requestLatLon + naptanId;

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        System.out.println(requestString);
        return client.target(requestString).request().get(String.class);
    }

    public static String sendRequest (String naptanId)
    {
        Request request       = new Request(naptanId);
        String  requestString = request.buildRequest(request.naptanId);

        Client client = null;

        if (Main.inputType == InputType.STOP_CODE) { client = ClientBuilder.newBuilder().register(StopPoint.class).register(JacksonFeature.class).build(); }
        else if (Main.inputType == InputType.POST_CODE) client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        System.out.println(requestString);
        return client.target(requestString).request().get(String.class);
    }
}