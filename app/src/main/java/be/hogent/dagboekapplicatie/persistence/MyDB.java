package be.hogent.dagboekapplicatie.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by jbuy519 on 23/10/2014.
 */
public class MyDB {

    private static final String TAG = "MyDB.class";
    private SQLiteDatabase db;
    private final Context context;
    private final MyDBHelper dbHelper;

    /**
     * Initialises this DB. Make sure the dbHelper object is also initialised.
     * @param context
     */
    public MyDB(Context context) {
        this.context = context;
        dbHelper = new MyDBHelper(context,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
    }

    /**
     * Closes the database
     */
    public void close(){
        db.close();
    }
    /**
     * Tries to open a writable database. If this is not possible, open a readable database.
     * Log the necessary messages for debugging purposes.
     */
    public void open(){
        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            Log.e(TAG,"Could not create a writeable database. Readable database has been opened");
            Log.e(TAG,e.getMessage());
            db = dbHelper.getReadableDatabase();
        }
    }

    /**
     * Adds an diary entry to the database.
     * http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#insert%28java.lang.String,%20java.lang.String,%20android.content.ContentValues%29
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insertDiary(ContentValues newDiaryValue){
        try{
            newDiaryValue.put(Constants.DATE_NAME,System.currentTimeMillis());
            return db.insert(Constants.TABLE_NAME,null,newDiaryValue);
        }catch(SQLiteException e){
            Log.e(TAG,"Inserting into database did not work");
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }

    /**
     * Fetches all diary entries from the database
     * @return The diary entries from the database.
     */
    public Cursor getDiaries(){
        Cursor c = db.query(Constants.TABLE_NAME,null,null,null,null,null,null);
        return c;
    }
}
