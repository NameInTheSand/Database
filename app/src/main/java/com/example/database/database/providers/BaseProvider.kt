package com.example.database.database.providers

import android.content.ContentValues
import com.example.database.DatabaseApp
import com.example.database.database.DBHelper

private const val DEFAULT_VALUE = 0L

abstract class BaseProvider<T> {

    private val dbHelper = DBHelper(DatabaseApp.appContext)
    protected val database = dbHelper.writableDatabase
    protected val contentValues = ContentValues()

    open suspend fun insertEntity(entity: T): Long = DEFAULT_VALUE

    open suspend fun getListEntities(): List<T> = emptyList()

    open suspend fun updateEntity(entity: T): Int = DEFAULT_VALUE.toInt()

    open suspend fun deleteEntity(id: Int): Int = DEFAULT_VALUE.toInt()

    open suspend fun clearTable(): Int = DEFAULT_VALUE.toInt()

}