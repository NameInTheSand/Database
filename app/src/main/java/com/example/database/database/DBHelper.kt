package com.example.database.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.database.database.tables.PersonTable

private const val DBNAME = "TestDB"
private const val VERSION = 1

class DBHelper(context: Context) : SQLiteOpenHelper(context, DBNAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        PersonTable.createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}