package com.yusufmendes.zikirmatik.domain.usecases.home

import android.content.Context
import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import javax.inject.Inject

class OpenPlayStoreUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun openPlayStore(context: Context,developerId: String) = homeRepository.openPlayStore(context,developerId)
}