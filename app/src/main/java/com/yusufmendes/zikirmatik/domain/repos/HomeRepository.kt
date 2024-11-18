package com.yusufmendes.zikirmatik.domain.repos

import android.content.Context
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun insertCounter(counterEntity: CounterEntity)

    fun openPlayStore(context: Context): Flow<Boolean>

    suspend fun updateCount(count: Int, countId: Int, date: String)
}