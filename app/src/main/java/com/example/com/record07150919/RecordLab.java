package com.example.com.record07150919;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 10449 on 2017/3/7.
 */

public class RecordLab {
    private static RecordLab sRecordLab;
    private List<Record> mRecords;

    public void addRecord(Record record) {
        mRecords.add(record);
    }

    public static RecordLab get(Context context) {
        if (sRecordLab == null) {
            sRecordLab = new RecordLab(context);
        }
        return sRecordLab;
    }

    private RecordLab(Context context) {
        mRecords = new ArrayList<>();

    }

    public List<Record> getRecords() {
        return mRecords;
    }

    public Record getRecord(UUID id) {
        for (Record record : mRecords) {
            if (record.getmId().equals(id)) {
                return record;
            }
        }
        return null;
    }

}
