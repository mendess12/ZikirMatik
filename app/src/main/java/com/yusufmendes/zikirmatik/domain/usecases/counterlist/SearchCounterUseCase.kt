package com.yusufmendes.zikirmatik.domain.usecases.counterlist

import com.yusufmendes.zikirmatik.domain.repos.CounterRepository
import javax.inject.Inject

class SearchCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend fun searchCounter(title: String) = counterRepository.searchCounter(title)
}