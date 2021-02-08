package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.ErrorEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

class LocalDataSource : DataSource {
    override fun getDashboard(): Single<DashboardEntity> {
        throw ErrorEntity(ErrorEntity.Code.NOT_IMPLEMENTED_LOCAL_DATASOURCE)
    }

    override fun getSection(href: String): Single<SectionEntity> {
        throw ErrorEntity(ErrorEntity.Code.NOT_IMPLEMENTED_LOCAL_DATASOURCE)
    }
}
