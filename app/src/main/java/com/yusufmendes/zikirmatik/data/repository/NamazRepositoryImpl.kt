package com.yusufmendes.zikirmatik.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufmendes.zikirmatik.data.model.Namaz
import com.yusufmendes.zikirmatik.domain.repos.NamazRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NamazRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
) : NamazRepository {
    override suspend fun getNamazList(): List<Namaz> =
        database.collection("Namaz").get().await().toObjects(Namaz::class.java)
}