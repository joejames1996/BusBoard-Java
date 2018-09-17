package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Request
{
    private String naptanId;
    private static String requestPrefix = "https://api.tfl.gov.uk/StopPoint/";
    private static String requestSuffix = "/Arrivals?app_id=ad5c6319&app_key=5df3db201bb778d8ba63676ad04a21e7";

    public Request(String naptanId)
    {
        this.naptanId = naptanId;
    }

    private static String buildRequest(String naptanId)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestPrefix);
        stringBuilder.append(naptanId);
        stringBuilder.append(requestSuffix);
        return stringBuilder.toString();
    }

    public static String sendRequest(String naptanId)
    {
        Request request = new Request(naptanId);
        String requestString = request.buildRequest(request.naptanId);

        Client client = ClientBuilder.newBuilder().register(StopPoint.class).register(JacksonFeature.class).build();
        return client.target(requestString).request().get(String.class);
    }
}
