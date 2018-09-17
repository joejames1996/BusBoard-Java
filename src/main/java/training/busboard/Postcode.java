package training.busboard;

public class Postcode
{

    private final String postcode;
    private final String country;
    private final String longitude;
    private final String latitude;
    private final String incode;
    private final String outcode;

    public Postcode (String postcode, String country, String longitude, String latitude, String incode, String outcode)
    {
        this.postcode = postcode;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.incode = incode;
        this.outcode = outcode;
    }
}