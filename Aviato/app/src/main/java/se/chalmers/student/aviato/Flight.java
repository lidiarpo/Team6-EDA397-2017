package se.chalmers.student.aviato;

/**
 *  This will be our result object
 *
 * Created by gryphex on 2017-04-04.
 */

public class Flight{
    //Attributes for our FlightObject
    public String AirlineAlias;
    public String AirlineName;
    public String FlightNumber;
    public String SourceAirport;
    public String DestinationAirport;
    public String DestinationName;
    public String DepTime;
    public String ArrTime;

    //So we can logout an Arraylist of our FlightObject easily for debugging purposes
    @Override
    public String toString() {
        return "Flight{" +
                "AirlineAlias='" + AirlineAlias + '\'' +
                ", AirlineName='" + AirlineName + '\'' +
                ", FlightNumber='" + FlightNumber + '\'' +
                ", SourceAirport='" + SourceAirport + '\'' +
                ", DestinationAirport='" + DestinationAirport + '\'' +
                ", DestinationName='" + DestinationName + '\'' +
                ", DepTime='" + DepTime + '\'' +
                ", ArrTime='" + ArrTime + '\'' +
                '}';
    }
}
