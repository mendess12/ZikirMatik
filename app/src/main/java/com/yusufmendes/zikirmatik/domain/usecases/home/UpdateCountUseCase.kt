package com.yusufmendes.zikirmatik.domain.usecases.home

import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import javax.inject.Inject

class UpdateCountUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun updateCount(count: Int, countId: Int, date: String) =
        homeRepository.updateCount(count, countId, date)
}