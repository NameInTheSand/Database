package com.example.database.database.tables

import android.database.sqlite.SQLiteDatabase

class PersonTable {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val SURNAME = "surname"

        fun createTable(db: SQLiteDatabase) {
            db.execSQL(
                "create table if not exists testTable(" +
                        "$ID integer primary key autoincrement," +
                        "$NAME text," +
                        "$SURNAME text);"
            )
        }
    }

}