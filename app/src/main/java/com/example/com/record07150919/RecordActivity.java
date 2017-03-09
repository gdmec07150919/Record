package com.example.com.record07150919;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.com.record07150919.mFragments.RecordFragment;
import com.example.com.record07150919.mFragments.SingleFragmentActivity;

import java.util.UUID;

public class RecordActivity extends SingleFragmentActivity {
   /* public static final String EXTRA_RECORD_ID = "com.me.android.recording.record_id";*/
    public static final String EXTRA_RECORD_ID = "com.me.android.recording.record_id";
    public static Intent newIntent(Context packageCOntext , UUID recordId) {
        Intent intent = new Intent(packageCOntext,RecordActivity.class);
        intent.putExtra(EXTRA_RECORD_ID,recordId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID recordId = (UUID) getIntent().getSerializableExtra(EXTRA_RECORD_ID);
        return RecordFragment.newInstance(recordId);
    }
}
