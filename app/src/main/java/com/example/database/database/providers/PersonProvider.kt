package com.example.database.database.providers

import android.util.Log
import com.example.database.MainActivity
import com.example.database.database.entities.Person

private const val ID = "id"
private const val NAME = "name"
private const val SURNAME = "surname"

class PersonProvider : BaseProvider<Person>() {

    override fun insertEntity(entity: Person): Long {
        contentValues.clear()

        contentValues.put(NAME, entity.name)
        contentValues.put(SURNAME, entity.surname)

        return database.insert("testTable", null, contentValues)
    }

    override fun getListEntities(): List<Person> {
        return super.getListEntities()
    }

    override fun updateEntity(entity: Person): Long {
        return super.updateEntity(entity)
    }

    override fun deleteEntity(entity: Person): Long {
        return super.deleteEntity(entity)
    }

}