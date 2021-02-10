package com.okynk.viaplaytest.injection

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.google.gson.Gson
import com.okynk.viaplaytest.BuildConfig
import com.okynk.viaplaytest.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_LOGGING_INTERCEPTOR = "HTTP_LOGGING_INTERCEPTOR"
private const val CONNECTION_TIMEOUT = 60L

val apiModule = module {
    single { provideOkHttpClient(get(named(HTTP_LOGGING_INTERCEPTOR)), get()) }
    single { provideRetrofit<ApiService>(get(), get()) }
    single(named(HTTP_LOGGING_INTERCEPTOR)) {
        provideHttpLoggingInterceptor()
    }
}

private inline fun <reified T> provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): T {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://content.viaplay.se/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

private fun provideOkHttpClient(
    httpLoggingInterceptor: Interceptor,
    networkFlipperPlugin: NetworkFlipperPlugin
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)

    if (BuildConfig.DEBUG) {
        builder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
    }

    builder.addInterceptor(httpLoggingInterceptor)

    return builder.build()
}

private fun provideHttpLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return interceptor
}
