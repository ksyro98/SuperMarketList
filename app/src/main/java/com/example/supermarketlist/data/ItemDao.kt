package com.example.supermarketlist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.supermarketlist.data.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY id ASC")
    fun getAll(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Query("DELETE FROM items WHERE id = :itemId")
    suspend fun delete(itemId: Int)
}