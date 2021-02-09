package com.okynk.viaplaytest.util

sealed class Resource<out T>(
    open val source: Source
) {
    data class Success<T>(
        val data: T,
        override val source: Source = Source.UNDEFINED
    ) : Resource<T>(source)

    data class Error<T>(
        val error: Throwable,
        override val source: Source = Source.UNDEFINED
    ) : Resource<T>(source)
}

inline fun <R, T> Resource<T>.fold(
    onSuccess: (value: T) -> R,
    onError: (exception: Throwable) -> R
): R {
    return when (this) {
        is Resource.Success -> {
            onSuccess(data)
        }
        is Resource.Error -> {
            onError(error)
        }
    }
}

enum class Source {
    DATABASE,
    NETWORK,
    UNDEFINED
}