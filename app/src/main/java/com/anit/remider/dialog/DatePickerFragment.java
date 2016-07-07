package com.anit.remider.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by 79900 on 07.07.2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int year  = c.get(c.YEAR);
        int month = c.get(c.MONTH);
        int day   = c.get(c.DAY_OF_MONTH);




        return new DatePickerDialog(getActivity(),this,year,month,day);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
