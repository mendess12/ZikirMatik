package com.yusufmendes.zikirmatik.presentation.view.hadith

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.Hadith
import com.yusufmendes.zikirmatik.domain.usecases.hadith.GetHadithListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadithFragmentViewModel @Inject constructor(
    private val getHadithListUseCase: GetHadithListUseCase
) : ViewModel() {

    var hadithLiveData = MutableLiveData<List<Hadith>>()

    fun getHadithList() = viewModelScope.launch {
        hadithLiveData.postValue(getHadithListUseCase.getHadithList())
    }
}