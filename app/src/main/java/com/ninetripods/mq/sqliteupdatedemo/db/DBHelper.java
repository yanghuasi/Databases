package com.ninetripods.mq.sqliteupdatedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ninetripods.mq.sqliteupdatedemo.update.DataBaseHelper;

/**
 * Created by mq on 2017/10/22 上午11:09
 * mqcoder90@gmail.com
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";
    public static final int DB_VERSION = 3;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.e("TTT", "onCreate");
        Log.e("TTT", "DBDao.SQL_CREATE_TABLE is " + DBDao.SQL_CREATE_TABLE);
        int initDBVersion = 1;
        DataBaseHelper.initDB(database);
        onUpgrade(database, initDBVersion, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.e("TTT", "onUpgrade" + oldVersion + "," + newVersion);
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1:
                    DataBaseHelper.upToDbVersion2(database);
                    break;
                case 2:
                    DataBaseHelper.upToDbVersion3(database);
                    break;
                default:
                    break;
            }
        }
    }

}
