package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

interface DataSource {
    fun getDashboard(): Single<DashboardEntity>
    fun getSection(href: String): Single<SectionEntity>

    companion object {
        const val LOCAL = "LocalDataSource"
        const val REMOTE = "RemoteDataSource"
    }
}
