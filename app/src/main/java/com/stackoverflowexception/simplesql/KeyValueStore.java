package com.stackoverflowexception.simplesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by adrian on 10/03/15.
 */
public class KeyValueStore extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "keyValueDatabase";
    private static final String TABLE_NAME = "keyValueTable";

    private static final String KEY_ID = "id";
    private static final String KEY_KEY = "key";
    private static final String KEY_VALUE = "value";

    public KeyValueStore(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KEYVALUE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_KEY + " TEXT,"
                + KEY_VALUE + " TEXT" + ")";
        db.execSQL(CREATE_KEYVALUE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addEntry(Entry entry) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_KEY, entry.getKey());
        values.put(KEY_VALUE, entry.getValue());
        database.insert(TABLE_NAME, null, values);
        database.close(); // Closing _database connection
    }

    // Get value
    public String getValue(String key){

        String value = "";
        Cursor cursor = null;
        try {
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.rawQuery("SELECT " + KEY_KEY + ", " + KEY_VALUE + " FROM " +
                    TABLE_NAME + " where " + KEY_KEY + " LIKE '" + key + "' LIMIT 1", null);
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not get value from _database");
        }
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                value = cursor.getString(cursor.getColumnIndex(KEY_VALUE));
            }
        }
        return value;
    }

}
