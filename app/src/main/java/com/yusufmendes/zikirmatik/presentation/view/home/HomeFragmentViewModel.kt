package com.yusufmendes.zikirmatik.presentation.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.domain.usecases.home.AddCounterUseCase
import com.yusufmendes.zikirmatik.domain.usecases.home.OpenPlayStoreUseCase
import com.yusufmendes.zikirmatik.domain.usecases.home.UpdateCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val addCounterUseCase: AddCounterUseCase,
    private val playStoreUseCase: OpenPlayStoreUseCase,
    private val updateCountUseCase: UpdateCountUseCase
) : ViewModel() {

    var count = 0
    val countLiveData = MutableLiveData<Int>()
    val deleteCounterLiveData = MutableLiveData<Int>()

    private val _addCountSharedFlow = MutableSharedFlow<Unit>()
    val addCountSharedFlow: SharedFlow<Unit> = _addCountSharedFlow

    private val _playStoreSharedFlow = MutableSharedFlow<Boolean>()
    val playStoreSharedFlow: SharedFlow<Boolean> = _playStoreSharedFlow

    private val _updateCountSharedFlow = MutableSharedFlow<Unit>()
    val updateCountSharedFlow: SharedFlow<Unit> = _updateCountSharedFlow


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
        _addCountSharedFlow.emit(result)
    }

    fun openPlayStore(context: Context) = viewModelScope.launch {
        playStoreUseCase.openPlayStore(context).collect { result ->
            _playStoreSharedFlow.emit(result)
        }
    }

    fun updateCount(count: Int, countId: Int, date: String) = viewModelScope.launch {
        val result = updateCountUseCase.updateCount(count, countId, date)
        _updateCountSharedFlow.emit(result)
    }
}