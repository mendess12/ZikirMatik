package com.yusufmendes.zikirmatik.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufmendes.zikirmatik.data.model.NameOfAllah
import com.yusufmendes.zikirmatik.domain.repos.NameOfAllahRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NameOfAllahRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
) : NameOfAllahRepository {
    override suspend fun getNameOfAllahList(): List<NameOfAllah> =
        database.collection("namesOfAllah").get().await().toObjects(NameOfAllah::class.java)
}