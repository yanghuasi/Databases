package com.ninetripods.mq.sqliteupdatedemo.update;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ninetripods.mq.sqliteupdatedemo.db.DBDao;

/**
 * Created by mq on 2017/10/29 下午1:44
 * mqcoder90@gmail.com
 */

public class DataBaseHelper {

    /**
     * 初始化数据库
     *
     * @param database SQLiteDatabase
     */
    public static void initDB(SQLiteDatabase database) {
        database.execSQL(DBDao.SQL_CREATE_TABLE);
    }

    /**
     * 数据库升级到2的改变
     *
     * @param database SQLiteDatabase
     */
    public static void upToDbVersion2(SQLiteDatabase database) {
        String updateSql = "alter table " + DBDao.TABLE_NAME + " add column store varchar(5)";
        database.execSQL(updateSql);
    }

    /**
     * 数据库升级到3的改变
     *
     * @param database SQLiteDatabase
     */
    public static void upToDbVersion3(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("store", 100);
        database.update(DBDao.TABLE_NAME, values, null, null);
    }
}
