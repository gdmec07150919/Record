package com.example.com.record07150919;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Date;

/**
 * Created by 10449 on 2017/3/10.
 */

public class DatePickerFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);
        return new AlertDialog.Builder(getActivity()).setView(view).setTitle(R.string.date_picker).setPositiveButton(android.R.string.ok,null).create();
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);
        DatePickerFragment datePickerFragment= new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }
}
