package com.yusufmendes.zikirmatik.presentation.view.name_of_allah

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.NameOfAllah
import com.yusufmendes.zikirmatik.domain.usecases.nameofallah.GetNameOfAllahListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameOfAllahFragmentViewModel @Inject constructor(
    private val nameOfAllahListUseCase: GetNameOfAllahListUseCase
) : ViewModel() {

    var nameOfAllahLiveData = MutableLiveData<List<NameOfAllah>>()

    fun getNameOfAllahList() = viewModelScope.launch {
        nameOfAllahLiveData.value = nameOfAllahListUseCase.getNameOfAllahList()
    }
}