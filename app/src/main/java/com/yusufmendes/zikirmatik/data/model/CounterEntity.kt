package com.yusufmendes.zikirmatik.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

)
