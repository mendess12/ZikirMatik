package com.yusufmendes.zikirmatik.domain.usecases.counterlist

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.repos.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    fun searchCounter(title: String): Flow<List<CounterEntity>> =
        counterRepository.searchCounter(title)
}