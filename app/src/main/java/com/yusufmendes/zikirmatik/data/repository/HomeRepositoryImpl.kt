package com.yusufmendes.zikirmatik.data.repository

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.data.source.local.CounterDao
import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : HomeRepository {
    override suspend fun getAllCounters(): List<CounterEntity> = counterDao.getAllCounters()

    override suspend fun insertCounter(counterEntity: CounterEntity) =
        counterDao.insertCounter(counterEntity)

    override suspend fun deleteCounter(counterEntity: CounterEntity)  =
        counterDao.deleteCounter(counterEntity)
}