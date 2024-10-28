package com.yusufmendes.zikirmatik.presentation.view.namaz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.Namaz
import com.yusufmendes.zikirmatik.domain.usecases.namaz.GetNamazListUseCase
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NamazFragmentViewModel @Inject constructor(
    private val getNamazListUseCase: GetNamazListUseCase
) : ViewModel() {

    private var _namazLiveData = MutableLiveData<Resource<List<Namaz>>>()
    val namazLiveData: LiveData<Resource<List<Namaz>>> get() = _namazLiveData

    init {
        _namazLiveData = getNamazListUseCase.namazList
    }

    fun getNamazList() = viewModelScope.launch {
        getNamazListUseCase.getNamazList()
    }
}