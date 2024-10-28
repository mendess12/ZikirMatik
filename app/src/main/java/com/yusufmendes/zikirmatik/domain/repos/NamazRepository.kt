package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.Namaz

interface NamazRepository {

    suspend fun getNamazList() : List<Namaz>
}