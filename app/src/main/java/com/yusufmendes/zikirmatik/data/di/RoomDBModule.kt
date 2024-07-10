package com.yusufmendes.zikirmatik.data.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.yusufmendes.zikirmatik.data.source.local.CounterRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideCounterRoomDB(@ApplicationContext context: Context): CounterRoomDB =
        Room.databaseBuilder(context, CounterRoomDB::class.java, "counter_database").build()

    @Provides
    @Singleton
    fun provideCounterDao(counterRoomDB: CounterRoomDB) = counterRoomDB.counterDao()
}