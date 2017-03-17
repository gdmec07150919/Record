package com.example.com.record07150919;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.com.record07150919.SQL.RecordBaseHelper;
import com.example.com.record07150919.SQL.RecordCursorWrapper;
import com.example.com.record07150919.SQL.RecordDbSchema;
import com.example.com.record07150919.SQL.RecordDbSchema.RecordTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 10449 on 2017/3/7.
 */

public class RecordLab {
    private static RecordLab sRecordLab;
/*    private List<Record> mRecords;*/
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public void addRecord(Record record) {

       /* mRecords.add(record);*/
        ContentValues contentValues = getContentValues(record);
        mDatabase.insert(RecordTable.NAME, null, contentValues);
    }

    public static RecordLab get(Context context) {
        if (sRecordLab == null) {
            sRecordLab = new RecordLab(context);
        }
        return sRecordLab;
    }

    private RecordLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RecordBaseHelper(mContext).getWritableDatabase();
/*        mRecords = new ArrayList<>();*/
    }

    public List<Record> getRecords() {
        List<Record> recordList = new ArrayList<>();
        RecordCursorWrapper cursorWrapper = queryRecords(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                recordList.add(cursorWrapper.getRecord());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return recordList;
    }


    private static ContentValues getContentValues(Record record) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RecordTable.Cols.UUID, record.getmId().toString());
        contentValues.put(RecordTable.Cols.TITLE, record.getmTitle());
        contentValues.put(RecordTable.Cols.DATE, record.getmDate().getTime());
        contentValues.put(RecordTable.Cols.SOLVED, record.ismSolved() ? 1 : 0);
        return contentValues;
    }

    public void updateRecord(Record record) {
        String uuidString = record.getmId().toString();
        ContentValues contentValues = getContentValues(record);
        mDatabase.update(RecordTable.NAME, contentValues, RecordTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private RecordCursorWrapper queryRecords(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RecordTable.NAME,
                null,///Columns-null selects all columns
                whereClause,
                whereArgs,
                null,//groupBy
                null,//having
                null//orderBy
        );
        return new RecordCursorWrapper(cursor);
    }


    public Record getRecord(UUID id) {
        RecordCursorWrapper cursorWrapper = queryRecords(
                RecordTable.Cols.UUID + "=?",
                new String[]{id.toString()}
        );
        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getRecord();
        } finally {
            cursorWrapper.close();
        }

    }

}
