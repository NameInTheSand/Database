package com.example.database.database.providers

import android.util.Log
import com.example.database.database.entities.Person
import com.example.database.database.tables.PersonTable.Companion.ID
import com.example.database.database.tables.PersonTable.Companion.NAME
import com.example.database.database.tables.PersonTable.Companion.SURNAME

class PersonProvider : BaseProvider<Person>() {

    override fun insertEntity(entity: Person): Long {
        contentValues.clear()

        contentValues.put(NAME, entity.name)
        contentValues.put(SURNAME, entity.surname)

        return database.insert("testTable", null, contentValues)
    }

    override fun getListEntities(): List<Person> {
        Log.d("TEST_TAG", "Get all data")
        val persons = mutableListOf<Person>()
        try {
            val cursor = database.query(
                "testTable",
                null,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor.moveToFirst()) {
                val idColumnIndex = cursor.getColumnIndex(ID)
                val nameColumnIndex = cursor.getColumnIndex(NAME)
                val surnameColumnIndex = cursor.getColumnIndex(SURNAME)

                do {
                    persons.add(
                        Person(
                            id = cursor.getInt(idColumnIndex),
                            name = cursor.getString(nameColumnIndex),
                            surname = cursor.getString(surnameColumnIndex)
                        )
                    )
                } while (cursor.moveToNext())
            } else {
                Log.d("TEST_TAG", "Table is empty")
            }
            cursor.close()
        } catch (exception: Exception) {
            Log.e("TEST_TAG", exception.toString())
        }
        return persons
    }

    override fun updateEntity(entity: Person): Int {
        contentValues.clear()
        contentValues.put(NAME, entity.name)
        contentValues.put(SURNAME, entity.surname)

        return database.update(
            "testTable",
            contentValues,
            "id = ?",
            arrayOf(entity.id.toString())
        )
    }

    override fun deleteEntity(id: Int): Int {
        return database.delete(
            "testTable", "id = $id", null
        )
    }

    override fun clearTable(): Int {
        Log.d("TEST_TAG", "Clear data")
        return database.delete("testTable", null, null)
    }

}