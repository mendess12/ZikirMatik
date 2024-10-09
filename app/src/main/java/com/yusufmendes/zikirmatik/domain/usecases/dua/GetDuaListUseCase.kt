package com.yusufmendes.zikirmatik.domain.usecases.dua

import androidx.lifecycle.MutableLiveData
import com.yusufmendes.zikirmatik.data.model.Dua
import com.yusufmendes.zikirmatik.domain.repos.DuaRepository
import com.yusufmendes.zikirmatik.util.resources.Resource
import javax.inject.Inject

class GetDuaListUseCase @Inject constructor(
    private val duaRepository: DuaRepository
) {

    val duaList = MutableLiveData<Resource<List<Dua>>>()

    suspend fun getDuaList() {

        duaList.value = Resource.Loading

        duaList.value = if (duaRepository.getDuaList().isNotEmpty()) {
            Resource.Success(duaRepository.getDuaList())
        } else {
            Resource.Error("Dua listesi boş!")
        }
    }
}