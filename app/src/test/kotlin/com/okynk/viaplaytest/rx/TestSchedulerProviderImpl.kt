package com.okynk.viaplaytest.rx

import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler


class TestSchedulerProviderImpl(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()
}