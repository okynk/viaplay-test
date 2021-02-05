package com.okynk.viaplaytest.usecase.base

import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

abstract class BaseUseCase(private val scheduler: SchedulerProvider) {

    protected fun <T> composeObservable(task: () -> Observable<T>): Observable<T> {
        return task().subscribeOn(scheduler.io())
    }

    protected fun composeCompletable(task: () -> Completable): Completable {
        return task().subscribeOn(scheduler.io())
    }

    protected fun <T> composeSingle(task: () -> Single<T>): Single<T> {
        return task().subscribeOn(scheduler.io())
    }
}
