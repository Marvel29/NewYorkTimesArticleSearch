package com.gmail.marvelfds.nytimessearch.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by Core i7 on 5/18/2016.
 */
public class DatePickerFragment extends DialogFragment  {
    // Use the current time as the default values for the picker

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);

        int month = c.get(Calendar.MONTH);

        int day = c.get(Calendar.DAY_OF_MONTH);



        // Activity needs to implement this interface

        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();



        // Create a new instance of TimePickerDialog and return it

        return new DatePickerDialog(getActivity(), listener, year, month, day);


    }
}
