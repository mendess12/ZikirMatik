package com.yusufmendes.zikirmatik.domain.usecases.nameofallah

import com.yusufmendes.zikirmatik.data.model.NameOfAllah
import com.yusufmendes.zikirmatik.domain.repos.NameOfAllahRepository
import javax.inject.Inject

class GetNameOfAllahListUseCase @Inject constructor(
    private val nameOfAllahRepository: NameOfAllahRepository
) {
    suspend fun getNameOfAllahList(): List<NameOfAllah> = nameOfAllahRepository.getNameOfAllahList()
}