package com.genius.thirdeye_lll.banglamedicinereminder.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ThirdEye-lll on 2/4/2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "medicine_reminder";
    static final int DATABASE_VERSION = 1;

    public static final String ALARM_TABLE = "medicine_alarm";
    public static final String COLUMN_ALARM_ID = "_id";
    public static final String COLUMN_MEDICINE_NAME = "medicine_name";
    public static final String COLUMN_MEDICINE_DESCRIPTION = "medicine_description";
    public static final String COLUMN_MEDICINE_START_DAYS = "start_days";
    public static final String COLUMN_MEDICINE_END_DAYS = "end_days";
    public static final String COLUMN_MEDICINE_AT_TIME = "at_time";
    //public static final String COLUMN_MEDICINE_TONE = "alarm_tone";
    //public static final String COLUMN_MEDICINE_VIBRATE = "alarm_vibrate";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + ALARM_TABLE + " ( "
                + COLUMN_ALARM_ID + " INTEGER primary key autoincrement, "
                + COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "
                + COLUMN_MEDICINE_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_MEDICINE_START_DAYS + " TEXT NOT NULL, "
                + COLUMN_MEDICINE_END_DAYS + " TEXT NOT NULL, "
                + COLUMN_MEDICINE_AT_TIME + " TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE);
        onCreate(db);
    }
}
