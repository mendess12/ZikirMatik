package com.yusufmendes.zikirmatik.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusufmendes.zikirmatik.data.model.CounterEntity

@Database(entities = [CounterEntity::class], version = 1)
abstract class CounterRoomDB : RoomDatabase() {

    abstract fun counterDao(): CounterDao
}