package com.genius.thirdeye_lll.banglamedicinereminder.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.genius.thirdeye_lll.banglamedicinereminder.Activity.AddNewPill;
import com.genius.thirdeye_lll.banglamedicinereminder.Module.Medicine;

/**
 * Created by ThirdEye-lll on 2/4/2017.
 */

public class DBManager {

    private SQLiteDatabase sql;
    private DBHelper dbHelper;

    public DBManager(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void addingDataToTable(Medicine medicine){

        sql = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DBHelper.COLUMN_MEDICINE_NAME,medicine.getMedicineName());
        cv.put(DBHelper.COLUMN_MEDICINE_DESCRIPTION,medicine.getMedicineDescription());
        cv.put(DBHelper.COLUMN_MEDICINE_START_DAYS,medicine.getStartDate());
        cv.put(DBHelper.COLUMN_MEDICINE_END_DAYS,medicine.getEndDate());
        cv.put(DBHelper.COLUMN_MEDICINE_AT_TIME,medicine.getAtTime());



        sql.insert(DBHelper.ALARM_TABLE,null,cv);
        sql.close();


    }


    public String[] fetchAllData(){
        sql = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM "+DBHelper.ALARM_TABLE;

        Cursor mcursor = sql.rawQuery(query,null);

        String [] receivedData = new String[mcursor.getCount()];

        mcursor.moveToFirst();


        if (mcursor.moveToFirst()){
            int counter = 0;
            do{
                receivedData[counter] = mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_NAME+""))+"\n"+
                        mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_DESCRIPTION))+"\n"+
                        mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_START_DAYS))+"\n"+
                        mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_END_DAYS))+"\n"+
                        mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_AT_TIME))+"\n\n";

              /*     int time = Integer.parseInt(mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_AT_TIME)).toString());

             if (time >=6 && time <=11){
                    receivedData[counter] = mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_NAME+""))+"\n"+
                            mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_DESCRIPTION))+"\n"+
                            mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_START_DAYS))+"\n"+
                            mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_END_DAYS))+"\n"+
                            mcursor.getString(mcursor.getColumnIndex(DBHelper.COLUMN_MEDICINE_AT_TIME))+"\n\n";

                }
*/
                counter = counter+1;
            }while (mcursor.moveToNext());
        }

        return receivedData;
    }

    

}
