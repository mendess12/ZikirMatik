package com.yusufmendes.zikirmatik.domain.usecases.namaz

import androidx.lifecycle.MutableLiveData
import com.yusufmendes.zikirmatik.data.model.Namaz
import com.yusufmendes.zikirmatik.domain.repos.NamazRepository
import com.yusufmendes.zikirmatik.util.resources.Resource
import javax.inject.Inject

class GetNamazListUseCase @Inject constructor(
    private val namazRepository: NamazRepository
) {
    val namazList = MutableLiveData<Resource<List<Namaz>>>()

    suspend fun getNamazList() {

        namazList.value = Resource.Loading

        namazList.value = if (namazRepository.getNamazList().isNotEmpty()) {
            Resource.Success(namazRepository.getNamazList())
        } else {
            Resource.Error("Namaz listesi boş!")
        }
    }
}