package com.yusufmendes.zikirmatik.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yusufmendes.zikirmatik.data.model.CounterEntity

@Dao
interface CounterDao {

    @Query("SELECT * FROM counter_table")
    suspend fun getAllCounters(): List<CounterEntity>

    @Insert
    suspend fun insertCounter(counterEntity: CounterEntity)

    @Delete
    suspend fun deleteCounter(counterEntity: CounterEntity)

}