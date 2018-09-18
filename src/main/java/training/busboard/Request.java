package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
public class Request
{
    public static final String requestPrefixLatLon = "https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&radius=500";
    public static final String requestPrefixTravel = "https://api.tfl.gov.uk/StopPoint/";
    public static final String requestSuffixTravel = "/Arrivals?app_id=ad5c6319&app_key=5df3db201bb778d8ba63676ad04a21e7";
    public static final String requestPrefixPostcode = "http://api.postcodes.io/postcodes/";

    public static String buildRequest (String naptanId, InputType inputType) throws Exception
    {
        StringBuilder stringBuilder = new StringBuilder();
        switch (inputType)
        {
            case STOP_CODE:
                stringBuilder.append(requestPrefixTravel);
                stringBuilder.append(naptanId);
                stringBuilder.append(requestSuffixTravel);
                break;
            case POST_CODE:
                stringBuilder.append(requestPrefixPostcode);
                stringBuilder.append(naptanId);
                break;
            case LAT_LONG:
                stringBuilder.append(requestPrefixLatLon);
                stringBuilder.append(naptanId);
                break;
        }

        return stringBuilder.toString();
    }

    public static String sendRequest (String naptanId, InputType inputType) throws Exception
    {
        String  requestString = Request.buildRequest(naptanId, inputType);

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        System.out.println(requestString + " " + inputType);
        String result = client.target(requestString).request().get(String.class);
        client.close();
        return result;
    }
}