package com.okynk.viaplaytest.feature.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.okynk.viaplaytest.model.MessageDialogEntity
import com.okynk.viaplaytest.model.toMessageDialogEntity
import com.okynk.viaplaytest.util.SingleLiveEvent
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber

abstract class BaseViewModel(application: Application, private val scheduler: SchedulerProvider) :
    AndroidViewModel(application) {

    protected val mShowMessageDialog = SingleLiveEvent<MessageDialogEntity>()
    val showMessageDialog: LiveData<MessageDialogEntity> = mShowMessageDialog

    protected val mShowLoadingOverlay = SingleLiveEvent<Boolean>()
    val showLoadingOverlay: LiveData<Boolean> = mShowLoadingOverlay

    protected val mCloseActivity = SingleLiveEvent<Void>()
    val closeActivity: LiveData<Void> = mCloseActivity

    protected val mGoBack = SingleLiveEvent<Void>()
    val goBack: LiveData<Void> = mGoBack

    private val disposable = CompositeDisposable()

    private val defaultOnStart = {
        mShowLoadingOverlay.postValue(true)
    }

    private val defaultOnEnd = {
        mShowLoadingOverlay.postValue(false)
    }

    private val defaultOnError: Function1<Throwable, Unit> = { error ->
        Timber.e(error)
        mShowMessageDialog.postValue(error.toMessageDialogEntity())
    }

    abstract fun start()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    protected fun <T> Observable<T>.execute(
        onError: Function1<Throwable, Unit> = defaultOnError,
        onStart: Function0<Unit> = defaultOnStart,
        onEnd: Function0<Unit> = defaultOnEnd,
        onComplete: Function0<Unit> = {},
        onNext: Function1<T, Unit>
    ) {
        disposable += this.observeOn(scheduler.ui())
            .doOnSubscribe {
                onStart()
            }
            .doFinally {
                onEnd()
            }
            .subscribeWith(
                object : DisposableObserver<T>() {
                    override fun onComplete() {
                        onComplete()
                    }

                    override fun onNext(t: T) {
                        onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                    }
                }
            )
    }

    protected fun <T> Single<T>.execute(
        onError: Function1<Throwable, Unit> = defaultOnError,
        onStart: Function0<Unit> = defaultOnStart,
        onEnd: Function0<Unit> = defaultOnEnd,
        onSuccess: Function1<T, Unit>
    ) {
        disposable += this.observeOn(scheduler.ui())
            .doOnSubscribe {
                onStart()
            }
            .doFinally {
                onEnd()
            }
            .subscribeWith(
                object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                    }
                }
            )
    }

    protected fun Completable.execute(
        onError: Function1<Throwable, Unit> = defaultOnError,
        onStart: Function0<Unit> = defaultOnStart,
        onEnd: Function0<Unit> = defaultOnEnd,
        onComplete: Function0<Unit> = {}
    ) {
        disposable += this.observeOn(scheduler.ui())
            .doOnSubscribe {
                onStart()
            }
            .doFinally {
                onEnd()
            }
            .subscribeWith(
                object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        onComplete()
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                    }
                }
            )
    }

    // Override this to handle fragment back pressed
    open fun onBackPressed() {
        mGoBack.call()
    }
}
