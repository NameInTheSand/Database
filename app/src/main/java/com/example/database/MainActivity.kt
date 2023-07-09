package com.example.database

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

        binding.apply {
            btnAdd.setOnClickListener {
                val insertCount = PersonProvider().insertEntity(
                    Person(
                        name = etName.text.toString(), surname = etSurname.text.toString()
                    )
                )
                Log.d("TEST_TAG", "Insert id = $insertCount")
            }
            btnClear.setOnClickListener {
                val clearCount = PersonProvider().clearTable()
                Log.d("TEST_TAG", "Clear count = $clearCount")
            }

            btnViewData.setOnClickListener {
                Log.d("TEST_TAG", PersonProvider().getListEntities().toString())
            }

            btnUpdate.setOnClickListener {
                val id = etId.text.toString()

                if (id.isEmpty()) return@setOnClickListener
                val updatedCount = PersonProvider().updateEntity(
                    Person(
                        id = id.toInt(),
                        name = etName.text.toString(),
                        surname = etSurname.text.toString()
                    )
                )
                Log.d("TEST_TAG", "Updated rows = $updatedCount")
            }

            btnDelete.setOnClickListener {
                val id = etId.text.toString()

                if (id.isEmpty()) return@setOnClickListener

                val deletedCount = PersonProvider().deleteEntity(id.toInt())
                Log.d("TEST_TAG", "Deleted rows = $deletedCount")
            }
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

}