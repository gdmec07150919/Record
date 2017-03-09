package com.example.com.record07150919.mFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.example.com.record07150919.R;
import com.example.com.record07150919.Record;
import com.example.com.record07150919.RecordActivity;
import com.example.com.record07150919.RecordLab;

import java.util.UUID;

public class RecordFragment extends Fragment {
    private static final String ARG_RECORD_ID = "record_id";

    private Record mRecord;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static RecordFragment newInstance(UUID recordId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECORD_ID,recordId);
        RecordFragment fragment = new RecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RecordFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // UUID recordId = (UUID)getActivity().getIntent().getSerializableExtra(RecordActivity.EXTRA_RECORD_ID);
        UUID recordId = (UUID) getArguments().getSerializable(ARG_RECORD_ID);
        mRecord = RecordLab.get(getActivity()).getRecord(recordId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record,container,false);
        mTitleField = (EditText) v.findViewById(R.id.record_title);
        mTitleField.setText(mRecord.getmTitle());

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.record_solved);
        mSolvedCheckBox.setChecked(mRecord.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mRecord.setmSolved(isChecked);
            }
        });


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }



}
