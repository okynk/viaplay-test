package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import com.okynk.viaplaytest.util.scheduler.SchedulerProviderImpl
import org.koin.dsl.module

val utilModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}