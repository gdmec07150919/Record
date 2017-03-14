package com.example.com.record07150919;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.com.record07150919.mFragments.RecordFragment;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RecordPagerActivity extends AppCompatActivity {
    private static final String EXTRA_RECORD_ID = "com.me.android.recording.record_id";
    private ViewPager mViewPager;
    private List<Record> mRecordList;


    public static Intent newIntent(Context packageContent, UUID recordId){
        Intent intent = new Intent(packageContent,RecordPagerActivity.class);
        intent.putExtra(EXTRA_RECORD_ID,recordId);
        return  intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_pager);
        UUID recordid = (UUID) getIntent().getSerializableExtra(EXTRA_RECORD_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_record_pager);
        mRecordList = RecordLab.get(this).getRecords();
        /*init();*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if(mRecordList.size() == 0){
                    return new RecordFragment();
                }
                Record record = mRecordList.get(position);
                return RecordFragment.newInstance(record.getmId());
            }

            @Override
            public int getCount() {

                return mRecordList.size();
            }
        });
        for(int i = 0;i< mRecordList.size();i++){
            if(mRecordList.get(i).getmId().equals(recordid)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
/*    public void init(){
        if(mRecordList.size() == 0)
        {
            Record record = new Record();
            record.setmDate(new Date());
            record.setmSolved(false);
            record.setmTitle("");
           RecordLab.get(this).addRecord(record);
            mRecordList = RecordLab.get(this).getRecord();
        }
    }*/

}
