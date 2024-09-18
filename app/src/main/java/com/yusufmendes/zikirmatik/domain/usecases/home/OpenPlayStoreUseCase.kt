package com.yusufmendes.zikirmatik.domain.usecases.home

import android.content.Context
import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenPlayStoreUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    fun openPlayStore(context: Context): Flow<Boolean> =
        homeRepository.openPlayStore(context)
}