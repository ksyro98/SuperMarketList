package com.example.supermarketlist.data

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ItemsViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: ItemsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    val allItems: LiveData<List<Item>> = repository.allItems

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