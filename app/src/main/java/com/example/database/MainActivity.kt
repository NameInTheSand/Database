package com.example.database

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.database.database.DBHelper
import com.example.database.database.entities.Person
import com.example.database.database.providers.PersonProvider
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dbHelper by lazy {
        DBHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contentValues = ContentValues()
        val db = dbHelper.writableDatabase

        binding.apply {
            btnAdd.setOnClickListener {
                val insertCount = PersonProvider().insertEntity(
                    Person(
                        name = etName.text.toString(),
                        surname = etSurname.text.toString()
                    )
                )
                Log.d("TEST_TAG", "Insert id = $insertCount")
            }
            btnClear.setOnClickListener {
                Log.d("TEST_TAG", "Clear data")

                val clearCount = db.delete("testTable", null, null)
                Log.d("TEST_TAG", "Clear count = $clearCount")
            }

            btnViewData.setOnClickListener {
                Log.d("TEST_TAG", "Get all data")

                val cursor = db.query(
                    "testTable",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )

                if (cursor.moveToFirst()) {
                    val idColumnIndex = cursor.getColumnIndex("id")
                    val nameColumnIndex = cursor.getColumnIndex("name")
                    val surnameColumnIndex = cursor.getColumnIndex("surname")

                    do {
                        Log.d(
                            "TEST_TAG", "Id = ${cursor.getInt(idColumnIndex)}" +
                                    "Name = ${cursor.getString(nameColumnIndex)}" +
                                    "Surname = ${cursor.getString(surnameColumnIndex)}"
                        )
                    } while (cursor.moveToNext())
                } else {
                    Log.d("TEST_TAG", "TAble is empty")
                }
                cursor.close()
            }

            btnUpdate.setOnClickListener {
                val id = etId.text.toString()

                if (id.isEmpty()) return@setOnClickListener

                contentValues.clear()

                contentValues.put("name", etName.text.toString())
                contentValues.put("surname", etSurname.text.toString())

                val updatedCount = db.update(
                    "testTable",
                    contentValues,
                    "id = ?",
                    arrayOf(id)
                )
                Log.d("TEST_TAG", "Updated rows = $updatedCount")
            }

            btnDelete.setOnClickListener {
                val id = etId.text.toString()

                if (id.isEmpty()) return@setOnClickListener

                val deletedCount = db.delete("testTable", "id = $id", null)
                Log.d("TEST_TAG", "Deleted rows = $deletedCount")
            }
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }


    companion object {

    }

}