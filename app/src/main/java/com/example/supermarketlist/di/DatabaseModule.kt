package com.example.supermarketlist.di

import android.content.Context
import androidx.room.Room
import com.example.supermarketlist.data.AppDatabase
import com.example.supermarketlist.data.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "list_database.db"
        ).build()
    }

    @Provides
    fun provideDao(database: AppDatabase) : ItemDao{
        return database.itemDao()
    }

}