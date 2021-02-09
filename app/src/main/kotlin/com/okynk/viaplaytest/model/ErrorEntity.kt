package com.okynk.viaplaytest.model

import com.okynk.viaplaytest.R

data class ErrorEntity(
    val code: Int,
    override val message: String? = null
) : Throwable(message) {
    object Code {
        const val NOT_IMPLEMENTED_LOCAL_DATASOURCE = 1
        const val NOT_IMPLEMENTED_REMOTE_DATASOURCE = 2
        const val GENERAL_ERROR = 3
    }

    companion object {
        val GENERIC = ErrorEntity(Code.GENERAL_ERROR)
    }
}

fun Throwable.toMessageDialogEntity() = MessageDialogEntity(
    titleRes = R.string.general_title_error,
    messageRes = if (message.isNullOrEmpty()) R.string.general_label_error else null,
    message = message
)