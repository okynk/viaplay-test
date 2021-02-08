package com.okynk.viaplaytest.injection

import com.google.gson.Gson
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import com.okynk.viaplaytest.util.scheduler.SchedulerProviderImpl
import org.koin.dsl.module

val utilModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
    single { provideGson() }
}

private fun provideGson(): Gson {
    return Gson()
}