package com.genius.thirdeye_lll.banglamedicinereminder.TodayAllFragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genius.thirdeye_lll.banglamedicinereminder.Database.DBHelper;
import com.genius.thirdeye_lll.banglamedicinereminder.Database.DBManager;
import com.genius.thirdeye_lll.banglamedicinereminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Morning extends Fragment {

    private TextView tvResult;
    private DBManager dbManager;
    private DBHelper dbHelper;


    public Morning() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_morning, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvResult = (TextView) getActivity().findViewById(R.id.tv_result);
        dbManager = new DBManager(new DBHelper(getContext()));

        String[] data = dbManager.fetchAllData();
        int i;
        String s = "";
        String time = DBHelper.COLUMN_MEDICINE_AT_TIME;


        for (i=0; i<data.length; i++){

                s = s+data[i];


        }
        tvResult.setText(s);
    }
}
