package com.deepak.mydevelopment.broadcastreciver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dsk on 13-Apr-18.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="numberDB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE="create table "+DbContract.TABLE_NAME+
            "(id integer primary key autoincrement,"+DbContract.INCOMING_NUMBER+" text)";
    private static final String DROP="drop table if exists "+DbContract.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP);
        onCreate(sqLiteDatabase);
    }
    public void saveNumber(String number,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbContract.INCOMING_NUMBER,number);
        database.insert(DbContract.TABLE_NAME,null,contentValues);
    }

    public Cursor readNumber(SQLiteDatabase database)
    {
        String[] projection={"id",DbContract.INCOMING_NUMBER};
        return (database.query(DbContract.TABLE_NAME,projection,null,null,null,null,null));
    }
}
