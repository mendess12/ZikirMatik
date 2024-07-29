package com.yusufmendes.zikirmatik.presentation.view.name_of_allah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.NameOfAllah
import com.yusufmendes.zikirmatik.domain.usecases.nameofallah.GetNameOfAllahListUseCase
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameOfAllahFragmentViewModel @Inject constructor(
    private val nameOfAllahListUseCase: GetNameOfAllahListUseCase
) : ViewModel() {

    private var _nameOfAllahLiveData = MutableLiveData<Resource<List<NameOfAllah>>>()
    val nameOfAllahLiveData: LiveData<Resource<List<NameOfAllah>>> get() = _nameOfAllahLiveData

    init {
        _nameOfAllahLiveData = nameOfAllahListUseCase.nameOfAllahList
    }

    fun getNameOfAllahList() = viewModelScope.launch {
        nameOfAllahListUseCase.getNameOfAllahList()
    }
}