package com.example.supermarketlist.data

import androidx.lifecycle.LiveData
import com.example.supermarketlist.data.Item
import com.example.supermarketlist.data.ItemDao
import javax.inject.Inject

class ItemsRepository @Inject constructor(private val itemDao: ItemDao) {

    val allItems: LiveData<List<Item>> = itemDao.getAll()

    suspend fun insert(item: Item){
        itemDao.insert(item)
    }

    suspend fun delete(itemId: Int){
        itemDao.delete(itemId)
    }

}