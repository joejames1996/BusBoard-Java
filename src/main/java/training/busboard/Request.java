package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Request
{
    private static final String requestPrefixLatLon   = "https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&radius=500";
    private static final String requestPrefixTravel   = "https://api.tfl.gov.uk/StopPoint/";
    private static final String requestSuffixTravel   = "/Arrivals?app_id=ad5c6319&app_key=5df3db201bb778d8ba63676ad04a21e7";
    private static final String requestPrefixPostcode = "http://api.postcodes.io/postcodes/";

    public static String sendStopPointRequest(String naptanId) throws Exception
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestPrefixTravel);
        stringBuilder.append(naptanId);
        stringBuilder.append(requestSuffixTravel);
        String request = stringBuilder.toString();

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String result = client.target(request).request().get(String.class);
        client.close();

        return result;
    }

    public static String sendPostCodeRequest(String postcode) throws Exception
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestPrefixPostcode);
        stringBuilder.append(postcode);
        String request = stringBuilder.toString();

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String result = client.target(request).request().get(String.class);
        client.close();

        return result;
    }

    public static String sendLatLonRequest(double lat, double lon)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestPrefixLatLon);
        stringBuilder.append(String.format("&lat=%f", lat));
        stringBuilder.append(String.format("&lon=%f", lon));
        String request = stringBuilder.toString();

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String result = client.target(request).request().get(String.class);
        client.close();

        return result;
    }
}