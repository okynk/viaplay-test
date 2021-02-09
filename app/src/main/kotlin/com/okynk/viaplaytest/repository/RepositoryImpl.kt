package com.okynk.viaplaytest.repository

import com.okynk.viaplaytest.datasource.DataSource
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val local: DataSource,
    private val remote: DataSource
) : Repository {
    override fun getDashboard(): Single<DashboardEntity> {
        return remote.getDashboard()
    }

    override fun getSection(link: LinkEntity): Single<SectionEntity> {
        return remote.getSection(link)
    }
}
