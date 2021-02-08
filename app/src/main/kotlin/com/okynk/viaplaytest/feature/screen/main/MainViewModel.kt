package com.okynk.viaplaytest.feature.screen.main

import android.app.Application
import com.okynk.viaplaytest.feature.base.BaseViewModel
import com.okynk.viaplaytest.usecase.UseCase
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import timber.log.Timber

class MainViewModel(
    application: Application,
    scheduler: SchedulerProvider,
    private val useCase: UseCase
) : BaseViewModel(application, scheduler) {
    override fun start() {
        useCase.getDashboard().execute(
            onError = {
                Timber.e(it)
            },
            onSuccess = {
                Timber.d(it.toString())
            }
        )
    }
}