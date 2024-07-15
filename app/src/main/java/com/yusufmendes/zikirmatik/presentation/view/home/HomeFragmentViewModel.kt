package com.yusufmendes.zikirmatik.presentation.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.usecases.home.AddCounterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val addCounterUseCase: AddCounterUseCase
) : ViewModel() {

    var addCounterLiveData = MutableLiveData<Unit>()
    var count = 0
    val countLiveData = MutableLiveData<Int>()
    val deleteCounterLiveData = MutableLiveData<Int>()

    fun getCounter() {
        count++
        countLiveData.postValue(count)
    }

    fun resetCounter() {
        count = 0
        deleteCounterLiveData.postValue(count)
    }

    fun addCounter(counterEntity: CounterEntity) = viewModelScope.launch {
        val result = addCounterUseCase.addCounter(counterEntity)
        addCounterLiveData.postValue(result)
    }
}