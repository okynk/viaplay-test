package com.okynk.viaplaytest.repository

import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getDashboard(): Single<DashboardEntity>
    fun getSection(link: LinkEntity): Single<SectionEntity>
}
