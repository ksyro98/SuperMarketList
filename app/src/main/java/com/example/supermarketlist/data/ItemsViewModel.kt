package com.example.supermarketlist.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.supermarketlist.data.AppDatabase
import com.example.supermarketlist.data.Item
import com.example.supermarketlist.data.ItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ItemsRepository
    val allItems: LiveData<List<Item>>

    init {
        val itemDao = AppDatabase.getDatabase(application).itemDao()
        repository = ItemsRepository(itemDao)
        allItems = repository.allItems
    }

    fun insert(itemName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Item(itemName = itemName))
        }
    }

    fun delete(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(itemId)
        }
    }

}