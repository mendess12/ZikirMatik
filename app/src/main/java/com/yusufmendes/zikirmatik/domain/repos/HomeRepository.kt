package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.CounterEntity

interface HomeRepository {

    suspend fun getAllCounters(): List<CounterEntity>

    suspend fun insertCounter(counterEntity: CounterEntity)

    suspend fun deleteCounter(counterEntity: CounterEntity)
}