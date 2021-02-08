package com.okynk.viaplaytest.model

import androidx.annotation.StringRes

data class MessageDialogEntity(
    val title: String? = null,
    @StringRes val titleRes: Int? = null,
    val message: String? = null,
    @StringRes val messageRes: Int? = null
)
