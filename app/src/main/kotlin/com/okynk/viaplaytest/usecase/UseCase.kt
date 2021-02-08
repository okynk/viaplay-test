package com.okynk.viaplaytest.usecase

import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

interface UseCase {
    fun getDashboard(): Single<DashboardEntity>
    fun getSection(href: String): Single<SectionEntity>
}
