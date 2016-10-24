package com.blues.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by blues on 2016/10/23.
 */

public final class HabitContract {

   private HabitContract(){}

   public static final class HabitEntry implements BaseColumns {

      public final static String TABLE_NAME = "habits";

      public final static String _ID = BaseColumns._ID;

      public final static String COLUMN_HABIT_NAME = "name";

       // count by hours
      public final static String COLUMN_HABIT_TIME = "time";

      public final static String COLUMN_HABIT_FEEL = "feeling";

      /**
       * Possible values for the feeling of the habit
       */
      public static final int HABIT_NORMAL = 0;
      public static final int HABIT_GOOD = 1;
      public static final int HABIT_BAD = 2;
   }
}
