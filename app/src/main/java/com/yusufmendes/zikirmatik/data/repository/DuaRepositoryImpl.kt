package com.yusufmendes.zikirmatik.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufmendes.zikirmatik.data.model.Dua
import com.yusufmendes.zikirmatik.domain.repos.DuaRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DuaRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
) : DuaRepository {
    override suspend fun getDuaList(): List<Dua> =
        database.collection("Dua").get().await().toObjects(Dua::class.java)
}