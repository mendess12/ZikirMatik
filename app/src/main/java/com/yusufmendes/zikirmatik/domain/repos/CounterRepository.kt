package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.CounterEntity

interface CounterRepository {

    suspend fun getCounterList(): List<CounterEntity>

    suspend fun  deleteCounter(counterEntity: CounterEntity)
}