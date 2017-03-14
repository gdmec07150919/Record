package com.example.com.record07150919.SQL;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.com.record07150919.Record;
import com.example.com.record07150919.SQL.RecordDbSchema.RecordTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 10449 on 2017/3/12.
 */

public class RecordCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public RecordCursorWrapper(Cursor cursor) {
        super(cursor);

    }
    public Record getRecord() {
        String uuidString = getString(getColumnIndex(RecordTable.Cols.UUID));
        String title = getString(getColumnIndex(RecordTable.Cols.TITLE));
        long date = getLong(getColumnIndex(RecordTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(RecordTable.Cols.SOLVED));

        Record record = new Record(UUID.fromString(uuidString));
        record.setmSolved(isSolved !=0);
        record.setmTitle(title);
        record.setmDate(new Date(date));
        return record;
    }


}
