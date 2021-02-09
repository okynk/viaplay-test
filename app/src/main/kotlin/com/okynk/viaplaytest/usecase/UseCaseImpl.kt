package com.okynk.viaplaytest.usecase

import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import com.okynk.viaplaytest.repository.Repository
import com.okynk.viaplaytest.usecase.base.BaseUseCase
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class UseCaseImpl(
    private val repository: Repository,
    scheduler: SchedulerProvider
) : BaseUseCase(scheduler), UseCase {
    override fun getDashboard(): Single<DashboardEntity> {
        return composeSingle { repository.getDashboard() }
    }

    override fun getSection(link: LinkEntity): Single<SectionEntity> {
        return composeSingle { repository.getSection(link) }
    }
}
