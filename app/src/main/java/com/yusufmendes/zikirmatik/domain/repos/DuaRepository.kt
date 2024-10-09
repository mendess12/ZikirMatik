package com.yusufmendes.zikirmatik.domain.repos

import com.yusufmendes.zikirmatik.data.model.Dua

interface DuaRepository {

  suspend fun getDuaList() : List<Dua>
}