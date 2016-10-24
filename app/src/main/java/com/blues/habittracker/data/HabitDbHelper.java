package com.blues.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blues.habittracker.data.HabitContract.HabitEntry;


/**
 * Created by blues on 2016/10/23.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_TIME + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_HABIT_FEEL + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
