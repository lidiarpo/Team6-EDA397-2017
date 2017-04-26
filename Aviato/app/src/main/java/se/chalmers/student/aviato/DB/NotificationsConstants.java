package se.chalmers.student.aviato.DB;
import se.chalmers.student.aviato.DB.NotificationsContract.NotificationEntry;

public final class NotificationsConstants {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NotificationEntry.TABLE_NAME + " (" +
                    NotificationEntry._ID + " INTEGER PRIMARY KEY," +
                    NotificationEntry.COLUMN_NAME_FLIGHT_ID + " TEXT," +
                    NotificationEntry.COLUMN_NAME_NOTIFICATION_TEXT + " TEXT," +
                    NotificationEntry.COLUMN_NAME_NOTIFICATION_READ + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NotificationEntry.TABLE_NAME;
}
