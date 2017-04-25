package se.chalmers.student.aviato;

/**
 * Created by Wasif Ali Moon on 4/25/2017.
 */

public class Notification {
    private String flightId;
    private String text;

    public Notification() {
    }

    public String getFlightId() {
        return flightId;
    }

    public String getText() {
        return text;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setText(String text) {
        this.text = text;
    }
}
