package com.yusufmendes.zikirmatik.domain.usecases.home

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import javax.inject.Inject

class AddCounterUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun addCounter(counterEntity: CounterEntity) =
        homeRepository.insertCounter(counterEntity)
}