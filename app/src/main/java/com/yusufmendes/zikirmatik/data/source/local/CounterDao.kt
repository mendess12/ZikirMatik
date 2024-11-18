package com.yusufmendes.zikirmatik.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {

    @Query("SELECT * FROM counter_table")
    fun getAllCounters(): Flow<List<CounterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounter(counterEntity: CounterEntity)

    @Delete
    suspend fun deleteCounter(counterEntity: CounterEntity)

    @Query("SELECT * FROM counter_table WHERE title LIKE :title")
    fun searchCounter(title: String): Flow<List<CounterEntity>>

    @Query("UPDATE counter_table SET counter = :count ,date = :date WHERE counterId = :countId")
    suspend fun updateCount(count :Int, countId:Int,date : String )
}