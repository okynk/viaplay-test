package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.database.DashboardDao
import com.okynk.viaplaytest.database.SectionsDao
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LocalDataSource(
    private val dashboardDao: DashboardDao,
    private val sectionsDao: SectionsDao
) : DataSource {
    override fun getDashboard(): Single<DashboardEntity> {
        return dashboardDao.getDashboard().toSingle()
    }

    override fun getSection(link: LinkEntity): Single<SectionEntity> {
        return sectionsDao.getSection(link.id).toSingle()
    }

    override fun saveDashboard(data: DashboardEntity): Completable {
        return dashboardDao.insert(data)
    }

    override fun saveSection(data: SectionEntity): Completable {
        return sectionsDao.insert(data)
    }
}
