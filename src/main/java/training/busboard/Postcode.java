package training.busboard;

public class Postcode
{

    public result result;

    public class result
    {
        public String postcode;
        public String country;
        public double longitude;
        public double latitude;
        public String outcode;
        public String incode;

        public String toString ()
        {
            return "Postcode: " + postcode + "\tCountry: " + country + "\tlongitude: " + longitude + "\tlatitude: " + latitude + "\tincode: " + incode + "\toutcode: " +
                   outcode;
        }

    }

    public String toString()
    {
        return result.toString();
    }

}