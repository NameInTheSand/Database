package com.example.database.database.providers

import android.content.ContentValues
import com.example.database.DatabaseApp
import com.example.database.database.DBHelper

private const val DEFAULT_VALUE = 0L

abstract class BaseProvider<T> {

    private val dbHelper = DBHelper(DatabaseApp.appContext)
    protected val database = dbHelper.writableDatabase
    protected val contentValues = ContentValues()

    open fun insertEntity(entity: T): Long = DEFAULT_VALUE

    open fun getListEntities(): List<T> = emptyList()

    open fun updateEntity(entity: T): Long = DEFAULT_VALUE

    open fun deleteEntity(entity: T): Long = DEFAULT_VALUE

}