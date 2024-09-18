package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import kotlinx.coroutines.flow.Flow

interface CounterRepository {

    fun getCounterList(): Flow<List<CounterEntity>>

    suspend fun deleteCounter(counterEntity: CounterEntity)

    suspend fun searchCounter(title: String): List<CounterEntity>
}