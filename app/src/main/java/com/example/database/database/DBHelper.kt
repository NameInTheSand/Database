package com.example.database.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val DBNAME = "TestDB"
private const val VERSION = 1

class DBHelper(context: Context) : SQLiteOpenHelper(context, DBNAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        Log.d("TEST_TAG", "OnCreateDB")
        db.execSQL("create table testTable(" +
                "id integer primary key autoincrement," +
                "name text," +
                "surname text);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}