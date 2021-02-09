package com.okynk.viaplaytest.model

import com.okynk.viaplaytest.R

data class ErrorEntity(
    val code: Int,
    override val message: String? = null
) : Throwable(message) {
    object Code {
        const val NOT_IMPLEMENTED_LOCAL_DATASOURCE = 1
        const val NOT_IMPLEMENTED_REMOTE_DATASOURCE = 2
    }
}

fun Throwable.toMessageDialogEntity() = MessageDialogEntity(
    titleRes = R.string.general_title_error,
    message = this.localizedMessage
)
