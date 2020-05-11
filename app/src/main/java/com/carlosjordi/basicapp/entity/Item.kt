package com.carlosjordi.basicapp.entity

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Long,
    val title: String,
    val description: String,
    val imgResource: Int?,
    val bitmap: Bitmap? = null
) : Parcelable