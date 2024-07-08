package com.yusufmendes.zikirmatik.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufmendes.zikirmatik.data.model.Hadith
import com.yusufmendes.zikirmatik.domain.repos.HadithRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HadithRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
) : HadithRepository {
    override suspend fun getHadithList(): List<Hadith> =
        database.collection("hadith").get().await().toObjects(Hadith::class.java)
}