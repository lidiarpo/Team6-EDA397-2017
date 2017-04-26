package se.chalmers.student.aviato.DB;

import android.provider.BaseColumns;

public class NotificationsContract {
    private NotificationsContract() {}

    public static class NotificationEntry implements BaseColumns {
        public static final String TABLE_NAME = "notifications";
        public static final String COLUMN_NAME_FLIGHT_ID = "flightId";
        public static final String COLUMN_NAME_NOTIFICATION_TEXT = "notificationText";
        public static final String COLUMN_NAME_NOTIFICATION_READ = "notificationRead";
    }
}
