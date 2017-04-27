package se.chalmers.student.aviato.notifications;

/**
 * Created by Wasif Ali Moon on 4/25/2017.
 */

public class Notification {
    private String mFlightId;
    private String mText;
    private int mRead;

    public Notification(String fId, String notification, int notificationRead) {
        mFlightId = fId;
        mText = notification;
        mRead = notificationRead;
    }

    public String getFlightId() {
        return mFlightId;
    }

    public String getText() {
        return mText;
    }

    public int getRead() {
        return mRead;
    }

    public void setFlightId(String flightId) {
        this.mFlightId = flightId;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public void setRead(int read) {
        this.mRead = read;
    }
}
