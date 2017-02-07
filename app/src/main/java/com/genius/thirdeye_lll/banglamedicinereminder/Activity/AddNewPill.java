package com.genius.thirdeye_lll.banglamedicinereminder.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.genius.thirdeye_lll.banglamedicinereminder.Database.DBHelper;
import com.genius.thirdeye_lll.banglamedicinereminder.Database.DBManager;
import com.genius.thirdeye_lll.banglamedicinereminder.MainActivity;
import com.genius.thirdeye_lll.banglamedicinereminder.Module.Medicine;
import com.genius.thirdeye_lll.banglamedicinereminder.R;
import com.genius.thirdeye_lll.banglamedicinereminder.TodayAllFragment.Morning;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewPill extends AppCompatActivity implements View.OnClickListener {
    //Calendar cal;

    private Toolbar toolbar;

    private DBManager dbManager;
    private EditText edMediName, edMediDes;

    private static RelativeLayout mStartDate, mEndDate, mAtTime, mRingtone, mVibration;
    private static TextView mStartDateTv, mEndDateTv, mAtTimeTv;
    public static String finalStartDate, finalEndDate, finalAtTime;
    private DatePickerDialogFragment mDatePickerDialogFragment;

    private Button savebtn;

    private int finalHour, finalMin;

    //---------------------------onCreate method here----------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pill);

        //setSupportActionBar(toolbar);
        //---------------initialize method using here--------------------------
        initViews();


        //-----------------------StartDate Listener here-------------------------
        mStartDate.setOnClickListener(this);


        //-----------------------EndDate Listener here-------------------------
        mEndDate.setOnClickListener(this);


        //-------------------------AtTime Listener is here--------------------------
        mAtTime.setOnClickListener(this);

        //TODO----------------------RingTone OnClick onclickListener------------------------------
       /* mRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Uri currentTone= RingtoneManager.getActualDefaultRingtoneUri(AddNewPill.this, RingtoneManager.TYPE_ALARM);
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentTone);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                startActivityForResult(intent, TONE_PICKER);
            }
        });*/


        //-----------------------------Vibration onClickListener --------------------------------



        //--------------------------main work here------------------------
        //----------------All data Stored in database using this button click listener-----------
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mediName = edMediName.getText().toString();
                String mediDes = edMediDes.getText().toString();

                Medicine medicineObj = new Medicine(mediName,mediDes,finalStartDate,finalEndDate,finalAtTime);
                dbManager.addingDataToTable(medicineObj);
                Toast.makeText(AddNewPill.this, "Data Stored Successfully!!!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


//TODO-------------------------------This is the Last parenthesis of onCreate Method--------------------------------------
    }

    //----------------------------initialize method -----------------------------------------
    void initViews() {

        mDatePickerDialogFragment = new DatePickerDialogFragment();
        dbManager = new DBManager(new DBHelper(getApplicationContext()));
        savebtn = (Button) findViewById(R.id.database_save_btn);
        mStartDateTv = (TextView) findViewById(R.id.startData_tv);
        mEndDateTv = (TextView) findViewById(R.id.endData_tv);
        mAtTimeTv = (TextView) findViewById(R.id.atTime_tv);

        edMediName = (EditText) findViewById(R.id.ed_mediName);
        edMediDes = (EditText) findViewById(R.id.ed_mediDescription);

        mStartDate = (RelativeLayout) findViewById(R.id.rl_btn_fromDate);
        mEndDate = (RelativeLayout) findViewById(R.id.rl_btn_toDate);
        mAtTime = (RelativeLayout) findViewById(R.id.rl_btn_atTime);
        //mRingtone = (RelativeLayout) findViewById(R.id.rl_btn_ringtone);
        //mVibration = (RelativeLayout) findViewById(R.id.rl__btn_vibration);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }


    //-------------------------AtTime OnClick view is here--------------------------
    @Override
    public void onClick(View view) {


        int id = view.getId();
        if (id == R.id.rl_btn_fromDate) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_START_DATE);
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
        } else if (id == R.id.rl_btn_toDate) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_END_DATE);
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
        }else if(id == R.id.rl_btn_atTime){

            final Calendar c = Calendar.getInstance();
            finalHour = c.get(Calendar.HOUR_OF_DAY);
            finalMin = c.get(Calendar.MINUTE);


            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            mAtTimeTv.setText(hourOfDay + ":" + minute);
                            finalAtTime = mAtTimeTv.getText().toString();
                        }
                    }, finalHour, finalMin, false);
            timePickerDialog.show();
        }



    }


    //********************************--------DatePickerDialogFragment CLASS------- **********************************************
    //-------------this is class is using DtaePickerDialog Fragment code here------------------------

    public static class DatePickerDialogFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        public static final int FLAG_START_DATE = 0;
        public static final int FLAG_END_DATE = 1;

        private int flag = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void setFlag(int i) {
            flag = i;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            if (flag == FLAG_START_DATE) {
                mStartDateTv.setText(format.format(calendar.getTime()));
                String sDate = mStartDateTv.getText().toString();
                mStartDateTv.setText(sDate);

                finalStartDate = sDate;
            } else if (flag == FLAG_END_DATE) {
                mEndDateTv.setText(format.format(calendar.getTime()));
                //TODO--------------------Start and End Date Result-----------------------------
                String eDate = mEndDateTv.getText().toString();

                mEndDateTv.setText(eDate);

                finalEndDate = eDate;
            }
        }
    }


    //--------------------------------this is for menu-------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newpill,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int _id = item.getItemId();

        if (_id == R.id.savebtn) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
