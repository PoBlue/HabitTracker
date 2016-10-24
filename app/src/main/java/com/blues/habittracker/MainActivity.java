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
        insertHabit();
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

        Log.i(LOG_TAG,"Table: " +  HabitEntry._ID + "-"
                + HabitEntry.COLUMN_HABIT_NAME + "-"
                + HabitEntry.COLUMN_HABIT_TIME + "-"
                + HabitEntry.COLUMN_HABIT_FEEL
        );

        try{
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int feelColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_FEEL);
            int timeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TIME);

            while (cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                int currentFeel = cursor.getInt(feelColumnIndex);

                Log.i(LOG_TAG,"Row: " +  currentID + "-"
                        + currentName + "-"
                        + currentTime + "-"
                        + currentFeel
                );
            }
        }
        finally {
            cursor.close();
        }

    }

    private void insertHabit(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Reading");
        values.put(HabitEntry.COLUMN_HABIT_TIME, 3);
        values.put(HabitEntry.COLUMN_HABIT_FEEL, HabitEntry.HABIT_GOOD);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
