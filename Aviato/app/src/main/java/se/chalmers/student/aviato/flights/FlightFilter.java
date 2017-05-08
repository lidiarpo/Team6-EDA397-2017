package se.chalmers.student.aviato.flights;


import java.util.List;

public interface FlightFilter {

    public List<Flight> filter(List<Flight> flights, String... parameters);

}