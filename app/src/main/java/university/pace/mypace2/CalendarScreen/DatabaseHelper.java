package university.pace.mypace2.CalendarScreen;

/**
 * Created by Mrgds on 6/23/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mrgds on 2/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "event.db";
    private static final String TABLE_NAME = "event";
    private static final String COLUMN_ID = "id";
    /***
     * This is for the data base
     ***/
    private static final String COLUMN_EVENT_ID = "eventid";
    /***
     * This is for the EventId Data
     ***/
    private static final String COLUMN_NAME = "eventname";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DAY = "day";


    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table events (id integer primary key not null," +
            "eventid text not null,eventname text not null,time text not null, day text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public String searchPass(String eventid) {
        String idCheck, nameCheck = "not Found";
        db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst())  //if at start
        {
            do {

                idCheck = cursor.getString(0);
                nameCheck = cursor.getString(1);
                if (idCheck.equals(eventid)) {     //checks with searched id name
                    nameCheck = cursor.getString(1);// returns event name in column
                    break;  //so out of loop
                }
            }
            while (cursor.moveToNext()); //move thorugh and find it
        }
        return nameCheck;

    }


    /***
     * Call this to put data in the database 'db'
     ***/
    public void insertEventData(EventData events) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = " Select * from events";   //* means all
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_EVENT_ID, events.getEventid());
        values.put(COLUMN_NAME, events.getName());
        values.put(COLUMN_TIME, events.getEventTime());
        values.put(COLUMN_DAY, events.getDay());

        db.insert(TABLE_NAME, null, values);      //inserts contacts into database
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;
        //this.db.execSQL(TABLE_CREATE);
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP  TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);


    }
}
