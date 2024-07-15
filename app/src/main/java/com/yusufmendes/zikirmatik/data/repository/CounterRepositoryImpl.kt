package com.yusufmendes.zikirmatik.data.repository

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.data.source.local.CounterDao
import com.yusufmendes.zikirmatik.domain.repos.CounterRepository
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : CounterRepository {
    override suspend fun getCounterList(): List<CounterEntity> = counterDao.getAllCounters()

    override suspend fun deleteCounter(counterEntity: CounterEntity) =
        counterDao.deleteCounter(counterEntity)

    override suspend fun searchCounter(title: String): List<CounterEntity> =
        counterDao.searchCounter("%$title%")
}