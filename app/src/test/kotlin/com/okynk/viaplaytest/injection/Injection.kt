package com.okynk.viaplaytest.injection

import android.app.Application
import android.content.Context
import com.okynk.viaplaytest.rx.TestSchedulerProviderImpl
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import io.mockk.mockkClass
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.koin.dsl.module

val testModule = module {
    single { mockkClass(Application::class) }
    single { mockkClass(Context::class) }
}

val testRxModule = module {
    single { TestScheduler() }
    single<SchedulerProvider> { TestSchedulerProviderImpl(get()) }
}
