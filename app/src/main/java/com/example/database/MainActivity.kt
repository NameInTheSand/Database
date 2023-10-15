package com.example.database

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.database.database.DBHelper
import com.example.database.database.viewModels.MainViewModel
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dbHelper by lazy {
        DBHelper(this)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnAdd.setOnClickListener {
                viewModel.insertEntity(
                    name = etName.text.toString(),
                    surname = etSurname.text.toString()
                )
            }
            btnClear.setOnClickListener {
                viewModel.clearPersonsTable()
            }

            btnViewData.setOnClickListener {
                viewModel.getListEntities()
            }

            btnUpdate.setOnClickListener {
                val id = etId.text.toString()

                if (id.isEmpty()) return@setOnClickListener
                viewModel.updateEntity(
                    id = id.toInt(),
                    name = etName.text.toString(),
                    surname = etSurname.text.toString()
                )
            }

            btnDelete.setOnClickListener {
                val id = etId.text.toString()

                if (id.isEmpty()) return@setOnClickListener
                viewModel.deleteEntity(id.toInt())
            }
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.allEntitiesLiveData.observe(this) {
            Log.d("TEST_TAG", it.toString())
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

}