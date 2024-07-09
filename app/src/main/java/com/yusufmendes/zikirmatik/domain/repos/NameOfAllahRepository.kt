package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.NameOfAllah

interface NameOfAllahRepository {

    suspend fun getNameOfAllahList(): List<NameOfAllah>
}