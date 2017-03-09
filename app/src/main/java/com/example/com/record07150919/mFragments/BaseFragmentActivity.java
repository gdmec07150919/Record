package com.example.com.record07150919.mFragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

/**
 * Created by 10449 on 2017/3/9.
 */

public class BaseFragmentActivity extends FragmentActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fm = getSupportFragmentManager();
        int index = requestCode >> 16;
        if(index != 0) {
            index--;
            if(fm.getFragments() == null || index < 0 || index >= fm.getFragments().size()) {
                return;
            }
        }
        Fragment frag = fm.getFragments().get(index);
        if(frag == null) {
            Log.w(TAG,"Activity result no fragment exists for index: 0x" + Integer.toHexString(requestCode));
        }else {
            handleResult(frag,requestCode,resultCode,data);
        }
        return;
    }

    private void handleResult(Fragment frag, int requestCode, int resultCode, Intent data) {
        frag.onActivityResult(requestCode & 0xffff,resultCode,data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if(frags != null) {
            for(Fragment f: frags){
                if(f != null) {
                    handleResult(f,requestCode,resultCode,data);
                }
            }
        }
    }

}
