package com.yusufmendes.zikirmatik.domain.usecases.hadith

import androidx.lifecycle.MutableLiveData
import com.yusufmendes.zikirmatik.data.model.Hadith
import com.yusufmendes.zikirmatik.domain.repos.HadithRepository
import com.yusufmendes.zikirmatik.util.resources.Resource
import javax.inject.Inject

class GetHadithListUseCase @Inject constructor(
    private val hadithRepository: HadithRepository
) {

    val hadithList = MutableLiveData<Resource<List<Hadith>>>()

    suspend fun getHadithList() {

        hadithList.value = Resource.Loading

        hadithList.value = if (hadithRepository.getHadithList().isNotEmpty()) {
            Resource.Success(hadithRepository.getHadithList())
        } else {
            Resource.Error(Throwable("Hadith list is empty"))
        }
    }
}