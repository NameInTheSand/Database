package com.example.database.database.providers

import android.util.Log
import com.example.database.database.entities.Person
import com.example.database.database.tables.PersonTable.Companion.ID
import com.example.database.database.tables.PersonTable.Companion.NAME
import com.example.database.database.tables.PersonTable.Companion.SURNAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonProvider : BaseProvider<Person>() {

    override suspend fun insertEntity(entity: Person): Long = withContext(Dispatchers.IO) {
        Log.d("TESTTAG", "current thread is ${Thread.currentThread().name}")
        contentValues.clear()

        contentValues.put(NAME, entity.name)
        contentValues.put(SURNAME, entity.surname)

        return@withContext database.insert("testTable", null, contentValues)
    }

    override suspend fun getListEntities(): List<Person> = withContext(Dispatchers.IO) {
        Log.d("TEST_TAG", "Get all data")
        Log.d("TESTTAG", "current thread is ${Thread.currentThread().name}")
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
        return@withContext persons
    }

    override suspend fun updateEntity(entity: Person): Int = withContext(Dispatchers.IO) {
        Log.d("TESTTAG", "current thread is ${Thread.currentThread().name}")
        contentValues.clear()
        contentValues.put(NAME, entity.name)
        contentValues.put(SURNAME, entity.surname)

        return@withContext database.update(
            "testTable",
            contentValues,
            "id = ?",
            arrayOf(entity.id.toString())
        )
    }

    override suspend fun deleteEntity(id: Int): Int = withContext(Dispatchers.IO) {
        Log.d("TESTTAG", "current thread is ${Thread.currentThread().name}")
        return@withContext database.delete(
            "testTable", "id = $id", null
        )
    }

    override suspend fun clearTable(): Int = withContext(Dispatchers.IO) {
        Log.d("TEST_TAG", "Clear data")
        Log.d("TESTTAG", "current thread is ${Thread.currentThread().name}")
        return@withContext database.delete("testTable", null, null)
    }

}