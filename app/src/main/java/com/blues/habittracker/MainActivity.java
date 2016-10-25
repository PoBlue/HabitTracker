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
        displayDatabaseInfo();
    }

    // Log the message about all rows in table
    private void displayDatabaseInfo(){

        Cursor cursor = getHabitAllDataCursor();

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

    private Cursor getHabitAllDataCursor() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] result_columns = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TIME,
                HabitEntry.COLUMN_HABIT_FEEL};

        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                result_columns,
                where,
                whereArgs,
                groupBy,
                having,
                order
        );

        return cursor;
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
