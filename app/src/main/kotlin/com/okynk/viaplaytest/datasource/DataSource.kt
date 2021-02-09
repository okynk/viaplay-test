package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface DataSource {
    fun getDashboard(): Single<DashboardEntity>
    fun getSection(link: LinkEntity): Single<SectionEntity>

    fun saveDashboard(data: DashboardEntity): Completable
    fun saveSection(data: SectionEntity): Completable

    companion object {
        const val LOCAL = "LocalDataSource"
        const val REMOTE = "RemoteDataSource"
    }
}
