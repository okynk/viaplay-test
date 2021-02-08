package com.okynk.viaplaytest.model

data class ErrorEntity(
    val code: Int,
    override val message: String? = null
) : Throwable(message) {
    object Code {
        const val NOT_IMPLEMENTED_LOCAL_DATASOURCE = 1
        const val NOT_IMPLEMENTED_REMOTE_DATASOURCE = 2
        const val GENERAL_ERROR = 3
        const val NETWORK_ERROR = 4
    }
}
