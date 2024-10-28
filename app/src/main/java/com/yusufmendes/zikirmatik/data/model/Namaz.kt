package com.yusufmendes.zikirmatik.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Namaz(
    val id: String? = "",
    val title: String? = "",
    val description: String? = "",
    val image_url: String = ""
):Parcelable
