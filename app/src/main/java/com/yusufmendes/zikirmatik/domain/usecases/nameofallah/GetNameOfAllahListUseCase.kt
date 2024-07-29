package com.yusufmendes.zikirmatik.domain.usecases.nameofallah

import androidx.lifecycle.MutableLiveData
import com.yusufmendes.zikirmatik.data.model.NameOfAllah
import com.yusufmendes.zikirmatik.domain.repos.NameOfAllahRepository
import com.yusufmendes.zikirmatik.util.resources.Resource
import javax.inject.Inject

class GetNameOfAllahListUseCase @Inject constructor(
    private val nameOfAllahRepository: NameOfAllahRepository
) {

    val nameOfAllahList = MutableLiveData<Resource<List<NameOfAllah>>>()

    suspend fun getNameOfAllahList() {

        nameOfAllahList.value = Resource.Loading

        nameOfAllahList.value = if (nameOfAllahRepository.getNameOfAllahList().isNotEmpty()) {
            Resource.Success(nameOfAllahRepository.getNameOfAllahList())
        } else {
            Resource.Error("Allah'ın isimleri listesi boş!")
        }
    }
}