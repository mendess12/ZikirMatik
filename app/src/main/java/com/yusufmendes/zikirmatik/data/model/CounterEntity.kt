package com.yusufmendes.zikirmatik.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "counter_table")
data class CounterEntity(

    @ColumnInfo(name = "userId")
    val userId: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "counterId")
    val counterId: Int = 0,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "counter")
    val counter: Int?,
    @ColumnInfo(name = "date")
    val date: String?

) : Parcelable
