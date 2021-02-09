package com.okynk.viaplaytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinkEntity(
    val id: String,
    val title: String,
    val href: String
) : Parcelable
