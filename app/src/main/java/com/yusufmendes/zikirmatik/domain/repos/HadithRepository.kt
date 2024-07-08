package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.Hadith

interface HadithRepository {

    suspend fun getHadithList(): List<Hadith>
}