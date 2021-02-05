package com.okynk.viaplaytest.usecase

import com.okynk.viaplaytest.repository.Repository
import com.okynk.viaplaytest.usecase.base.BaseUseCase
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider

class UseCaseImpl(
        private val repository: Repository,
        scheduler: SchedulerProvider
) : BaseUseCase(scheduler), UseCase {
//    override fun authAnonymously(): Completable {
//        return repository.authAnonymously()
//    }
//
//    override fun signOut(): Completable {
//        return repository.signOut()
//    }
}
