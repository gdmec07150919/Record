package com.example.com.record07150919;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 10449 on 2017/3/7.
 */

public class Record {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    public Record () {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public UUID getmId() {
        return mId;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
