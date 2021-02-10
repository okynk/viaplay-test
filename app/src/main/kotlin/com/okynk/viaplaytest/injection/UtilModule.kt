package com.okynk.viaplaytest.injection

import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.google.gson.Gson
import org.koin.dsl.module

val utilModule = module {
    single { provideGson() }
    single { NetworkFlipperPlugin() }
}

private fun provideGson(): Gson {
    return Gson()
}
