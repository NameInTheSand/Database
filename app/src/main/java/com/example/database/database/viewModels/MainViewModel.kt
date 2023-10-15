package com.example.database.database.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.database.entities.Person
import com.example.database.database.providers.PersonProvider
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _allEntitiesLiveData = MutableLiveData<List<Person>>()
    val allEntitiesLiveData: LiveData<List<Person>> = _allEntitiesLiveData

    fun insertEntity(name: String, surname: String) {
        viewModelScope.launch {
            val insertCount = PersonProvider().insertEntity(
                Person(name = name, surname = surname)
            )
            Log.d("TEST_TAG", "Insert id = $insertCount")
        }
    }

    fun clearPersonsTable() {
        viewModelScope.launch {
            val clearCount = PersonProvider().clearTable()
            Log.d("TEST_TAG", "Clear count = $clearCount")
        }
    }

    fun getListEntities() {
        viewModelScope.launch {
            _allEntitiesLiveData.value = PersonProvider().getListEntities()
        }
    }

    fun updateEntity(id: Int, name: String, surname: String) {
        viewModelScope.launch {
            val updatedCount = PersonProvider().updateEntity(
                Person(id = id, name = name, surname = surname)
            )
            Log.d("TEST_TAG", "Updated rows = $updatedCount")
        }
    }

    fun deleteEntity(id: Int) {
        viewModelScope.launch {
            val deletedCount = PersonProvider().deleteEntity(id.toInt())
            Log.d("TEST_TAG", "Deleted rows = $deletedCount")
        }
    }

}