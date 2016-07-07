package university.pace.mypace2.TestingPackage;

/**
 * Created by Mrgds on 6/23/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mrgds on 2/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "event.db";
    private static final String TABLE_NAME = "event";
    private static final String COLUMN_ID = "id";
    /***
     * This is for the data base create columns for each piece of data
     ***/
    private static final String COLUMN_EVENT_ID = "eventid";
    /***
     * This is for the EventId Data
     ***/
    private static final String COLUMN_NAME = "eventname";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DAY = "day";


    SQLiteDatabase db;

    private final String query = "CREATE TABLE" + TABLE_NAME + "(" +
            COLUMN_EVENT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + COLUMN_NAME + "STRING" + COLUMN_DAY
            + "STIRING" + ");";

    /****
     * AUTOINCREMENT INCREMNETS TABLE BYITSELF IT BENFITS THE PROGRAMMER 'LESS' CODING
     ****/
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public String searchPass(String eventid) {
        String idCheck, nameCheck = "NOT FOUND";
        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME; //* means all
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
    public void insertData(EventData events) {

        db = this.getWritableDatabase();  //initialize db
        ContentValues values = new ContentValues();

        String query = " SELECT * FROM EVENT";   //* means all
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_EVENT_ID, events.getEventid());
        values.put(COLUMN_NAME, events.getName());
        values.put(COLUMN_TIME, events.getEventTime());
        values.put(COLUMN_DAY, events.getDay());

        db.insert(TABLE_NAME, null, values);      //inserts contacts into database
    }

    /***
     * Call this to delete data in the database 'db'
     ***/


    public void deleteEventData(String name) {
        db = this.getWritableDatabase();  //initialize db
        db.execSQL("DELETE FROM" + TABLE_NAME + "WHERE"
                + COLUMN_NAME + " =\"" + name + "\";");

    }

    /***
     * Call this to print data in the database 'db'
     ***/


    public String printEventData(String name) {

        String dbString = "";
        db = this.getWritableDatabase();  //initialize db
        String query = " SELECT * FROM " + TABLE_NAME + "WHERE 1"; //SELECT EVERY  '*'
        //points to location                                      // COLUMN AND '1' EVERY ROW
        Cursor cursor = db.rawQuery(query, null);
        //move to first row
        cursor.moveToFirst();

        //not at the last row
        while (!cursor.isAfterLast()) {
            /* if something is in db
                  Loop through and extract the event name and return each
                          * */
            if (cursor.getString(cursor.getColumnIndex("event")) != null) {
                dbString += cursor.getString(cursor.getColumnIndex("event"));
                dbString += "\n";
            }


        }
        db.close();
        return dbString;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(query);


        /**  Log.e("CREATION", "Created");
         Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
        Log.e("CREATION", "Created");
         this.db = db;
         this.db.execSQL(TABLE_CREATE);
         **/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP  TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);


    }
}
