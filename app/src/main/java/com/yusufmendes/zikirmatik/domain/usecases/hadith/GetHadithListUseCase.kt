package com.yusufmendes.zikirmatik.domain.usecases.hadith

import com.yusufmendes.zikirmatik.data.model.Hadith
import com.yusufmendes.zikirmatik.domain.repos.HadithRepository
import javax.inject.Inject

class GetHadithListUseCase @Inject constructor(
    private val hadithRepository: HadithRepository
) {
    suspend fun getHadithList(): List<Hadith> = hadithRepository.getHadithList()
}