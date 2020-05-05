package com.carlosjordi.toolbars.toolbar_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Long,
    val title: String,
    val description: String,
    val imgResource: Int
) : Parcelable