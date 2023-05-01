package com.example.database

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.database.database.DBHelper
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
                contentValues.clear()
                Log.d("TEST_TAG", "Insert data")

                contentValues.put(NAME, etName.text.toString())
                contentValues.put(SURNAME, etSurname.text.toString())

                val rowId = db.insert("testTable", null, contentValues)
                Log.d("TEST_TAG", "Row id = $rowId")
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
        }
    }


    companion object {
        private const val NAME = "name"
        private const val SURNAME = "surname"
    }

}