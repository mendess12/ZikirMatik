package com.yusufmendes.zikirmatik.domain.usecases.counterlist

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.repos.CounterRepository
import javax.inject.Inject

class GetCounterListUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend fun getCounterList(): List<CounterEntity> = counterRepository.getCounterList()
}