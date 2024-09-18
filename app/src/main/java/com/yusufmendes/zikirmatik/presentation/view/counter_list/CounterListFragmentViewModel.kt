package com.yusufmendes.zikirmatik.presentation.view.counter_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.usecases.counterlist.DeleteCountUseCase
import com.yusufmendes.zikirmatik.domain.usecases.counterlist.GetCounterListUseCase
import com.yusufmendes.zikirmatik.domain.usecases.counterlist.SearchCounterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterListFragmentViewModel @Inject constructor(
    private val counterListUseCase: GetCounterListUseCase,
    private val deleteCountUseCase: DeleteCountUseCase,
    private val searchCounterUseCase: SearchCounterUseCase
) : ViewModel() {

    private val _counterListSharedFlow = MutableSharedFlow<List<CounterEntity>>()
    val counterListSharedFlow: SharedFlow<List<CounterEntity>> = _counterListSharedFlow

    var deleteCountLiveData = MutableLiveData<Unit>()

    private val _searchSharedFlow = MutableSharedFlow<List<CounterEntity>>()
    val searchSharedFlow: SharedFlow<List<CounterEntity>> = _searchSharedFlow

    fun getCounterList() = viewModelScope.launch {
        counterListUseCase.getCounterList().collect { result ->
            _counterListSharedFlow.emit(result)
        }
    }

    fun deleteCounter(counterEntity: CounterEntity) = viewModelScope.launch {
        val result = deleteCountUseCase.deleteCounter(counterEntity)
        deleteCountLiveData.postValue(result)
        getCounterList()
    }

    fun searchCounter(title: String) = viewModelScope.launch {
        searchCounterUseCase.searchCounter(title).collect { result ->
            _searchSharedFlow.emit(result)
        }
    }
}