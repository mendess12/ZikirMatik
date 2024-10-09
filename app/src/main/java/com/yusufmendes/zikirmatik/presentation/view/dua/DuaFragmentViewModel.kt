package com.yusufmendes.zikirmatik.presentation.view.dua

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.Dua
import com.yusufmendes.zikirmatik.domain.usecases.dua.GetDuaListUseCase
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DuaFragmentViewModel @Inject constructor(
    private val getDuaListUseCase: GetDuaListUseCase
) : ViewModel() {

    private var _duaLiveData = MutableLiveData<Resource<List<Dua>>>()
    val duaLiveData: LiveData<Resource<List<Dua>>> get() = _duaLiveData

    init {
        _duaLiveData = getDuaListUseCase.duaList
    }

    fun getDuaList() = viewModelScope.launch {
        getDuaListUseCase.getDuaList()
    }
}