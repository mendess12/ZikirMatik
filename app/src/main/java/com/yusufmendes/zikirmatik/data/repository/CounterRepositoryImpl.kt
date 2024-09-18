package com.yusufmendes.zikirmatik.data.repository

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.data.source.local.CounterDao
import com.yusufmendes.zikirmatik.domain.repos.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : CounterRepository {
    override fun getCounterList(): Flow<List<CounterEntity>> = counterDao.getAllCounters()

    override suspend fun deleteCounter(counterEntity: CounterEntity) =
        counterDao.deleteCounter(counterEntity)

    override fun searchCounter(title: String): Flow<List<CounterEntity>> =
        counterDao.searchCounter("%$title%")
}