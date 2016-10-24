package com.blues.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blues.habittracker.data.HabitContract.HabitEntry;
import com.blues.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
        diplayDatabaseInfo();
    }

    private void diplayDatabaseInfo(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TIME,
                HabitEntry.COLUMN_HABIT_FEEL};

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        Log.i(LOG_TAG,"db");

        try{
            Log.i(LOG_TAG,"table count: " + cursor.getCount());
        }
        finally {
            cursor.close();
        }

    }

    private void insertPet(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Reading");
        values.put(HabitEntry.COLUMN_HABIT_TIME, 3);
        values.put(HabitEntry.COLUMN_HABIT_FEEL, HabitEntry.HABIT_GOOD);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
