package com.yusufmendes.zikirmatik.data.repository

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.data.source.local.CounterDao
import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : HomeRepository {

    override suspend fun insertCounter(counterEntity: CounterEntity) =
        counterDao.insertCounter(counterEntity)

    override fun openPlayStore(context: Context): Flow<Boolean> = flow {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://play.google.com/store/apps/developer?id=Yusuf+Mendes")
        }
        try {
            context.startActivity(intent)
            emit(true)
        } catch (e: ActivityNotFoundException) {
            //play store tarayıcısı bulunmazsa
            emit(false)
        }
    }
}