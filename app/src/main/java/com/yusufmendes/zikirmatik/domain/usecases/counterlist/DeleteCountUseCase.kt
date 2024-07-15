package com.yusufmendes.zikirmatik.domain.usecases.counterlist

import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.repos.CounterRepository
import javax.inject.Inject

class DeleteCountUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend fun deleteCounter(counterEntity: CounterEntity) =
        counterRepository.deleteCounter(counterEntity)
}