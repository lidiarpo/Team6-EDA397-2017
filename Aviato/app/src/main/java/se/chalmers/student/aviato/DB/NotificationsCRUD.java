package se.chalmers.student.aviato.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.student.aviato.DB.NotificationsContract.NotificationEntry;
import se.chalmers.student.aviato.Notification;

public class NotificationsCRUD {
    NotificationsDbHelper notificationsDbHelper;
    SQLiteDatabase db;
    String TAG = "NotificationsCRUD";

    public NotificationsCRUD(NotificationsDbHelper notificationsDbHelper) {
        this.notificationsDbHelper = notificationsDbHelper;
    }

    public void addNotification(Notification notification){
        db = notificationsDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotificationEntry.COLUMN_NAME_FLIGHT_ID, notification.getFlightId());
        values.put(NotificationEntry.COLUMN_NAME_NOTIFICATION_TEXT, notification.getText());
        values.put(NotificationEntry.COLUMN_NAME_NOTIFICATION_READ, notification.getRead());
        long newRowId = db.insert(NotificationEntry.TABLE_NAME, null, values);
        Log.d(TAG,"Added new row to the notifications DB with ID:" + newRowId);
    }
    public void deleteNotification(String flightId){

        db = notificationsDbHelper.getWritableDatabase();

        String selection = NotificationEntry.COLUMN_NAME_FLIGHT_ID + " LIKE ?";
        String[] selectionArgs = {flightId};
        long newRowId = db.delete(NotificationEntry.TABLE_NAME, selection, selectionArgs);
        Log.d(TAG,"deleted row from the notifications DB with ID:" + newRowId);
    }

    public void updateNotification(Notification notification){
        deleteNotification(notification.getFlightId());
        addNotification(notification);
        Log.d(TAG,"updated row from the notifications DB with ID:" + notification.getFlightId());
    }

    public boolean existsNotification(String flightId){
        db = notificationsDbHelper.getReadableDatabase();
        String Query = "Select * from " + NotificationEntry.TABLE_NAME + " where " + NotificationEntry.COLUMN_NAME_FLIGHT_ID + " = " + flightId;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            Log.d(TAG,"doesnot exist with ID:" + flightId);
            return false;
        }
        cursor.close();
        Log.d(TAG,"exist with ID:" + flightId);
        return true;
    }

    public List<Notification> readNotifications(){

        db = notificationsDbHelper.getReadableDatabase();
        List<Notification> notifications = new ArrayList<Notification>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                NotificationEntry.COLUMN_NAME_FLIGHT_ID,
                NotificationEntry.COLUMN_NAME_NOTIFICATION_TEXT,
                NotificationEntry.COLUMN_NAME_NOTIFICATION_READ};

        Cursor cursor = db.query(
                NotificationEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            Notification notification = new Notification(cursor.getString(0), cursor.getString(1), cursor.getInt(2));
            //notification.setFlightId();
            //notification.setText(cursor.getString(1));
            Log.d(TAG,notification.toString());
            notifications.add(notification);
        }
        cursor.close();
        Log.d(TAG, notifications.toString());
        return notifications;
    }
}
