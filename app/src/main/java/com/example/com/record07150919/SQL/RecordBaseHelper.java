package com.example.com.record07150919.SQL;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.com.record07150919.SQL.RecordDbSchema.RecordTable;

/**
 * Created by 10449 on 2017/3/11.
 */

public class RecordBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "recordBase.db";

    public RecordBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String lang = "create table " + RecordTable.NAME + "(" + "_id integer primary key autoincrement, " + RecordTable.Cols.UUID + ", " + RecordTable.Cols.TITLE + ", " + RecordTable.Cols.DATE + ", " + RecordTable.Cols.SOLVED + ")";
        Log.v("aabbcc",lang);
        db.execSQL(lang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
