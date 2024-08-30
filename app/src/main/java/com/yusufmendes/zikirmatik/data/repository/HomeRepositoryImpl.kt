package com.yusufmendes.zikirmatik.data.repository

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.data.source.local.CounterDao
import com.yusufmendes.zikirmatik.domain.repos.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : HomeRepository {
    override suspend fun getAllCounters(): List<CounterEntity> = counterDao.getAllCounters()

    override suspend fun insertCounter(counterEntity: CounterEntity) =
        counterDao.insertCounter(counterEntity)

    override suspend fun deleteCounter(counterEntity: CounterEntity) =
        counterDao.deleteCounter(counterEntity)

    override suspend fun openPlayStore(context:Context, developerId: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://play.google.com/store/apps/developer?id=Yusuf+Mendes")
        }
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
           //play store tarayıcısı bulunmazsa
        }
    }
}