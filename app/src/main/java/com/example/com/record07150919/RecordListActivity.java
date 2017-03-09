package com.example.com.record07150919;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.com.record07150919.mFragments.RecordListFrament;
import com.example.com.record07150919.mFragments.SingleFragmentActivity;

public class RecordListActivity extends SingleFragmentActivity {
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        Log.v("aaabbb","kaishi");
    }*/

    @Override
    protected Fragment createFragment() {
        return new RecordListFrament();
    }
}
