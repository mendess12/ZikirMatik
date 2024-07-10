package com.yusufmendes.zikirmatik.presentation.view.counter_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.usecases.counterlist.GetCounterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterListFragmentViewModel @Inject constructor(
    private val counterListUseCase: GetCounterListUseCase
) : ViewModel() {

    var counterListLiveData = MutableLiveData<List<CounterEntity>>()

    fun getCounterList() = viewModelScope.launch {
        val result = counterListUseCase.getCounterList()
        counterListLiveData.postValue(result)
    }
}