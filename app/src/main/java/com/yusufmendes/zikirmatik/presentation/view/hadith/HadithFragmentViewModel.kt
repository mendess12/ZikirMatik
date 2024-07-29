package com.yusufmendes.zikirmatik.presentation.view.hadith

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.Hadith
import com.yusufmendes.zikirmatik.domain.usecases.hadith.GetHadithListUseCase
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadithFragmentViewModel @Inject constructor(
    private val getHadithListUseCase: GetHadithListUseCase
) : ViewModel() {

    private var _hadithLiveData = MutableLiveData<Resource<List<Hadith>>>()
    val hadithLiveData: LiveData<Resource<List<Hadith>>> get() = _hadithLiveData

    init {
        _hadithLiveData = getHadithListUseCase.hadithList
    }

    fun getHadithList() = viewModelScope.launch {
        getHadithListUseCase.getHadithList()
    }
}