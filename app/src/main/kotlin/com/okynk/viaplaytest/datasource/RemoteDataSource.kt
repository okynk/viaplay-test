package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.api.ApiService
import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.mapper.Mapper
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.ErrorEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import com.okynk.viaplaytest.util.extensions.cleanHref
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RemoteDataSource(
    private val apiService: ApiService,
    private val mapperDashboard: Mapper<DashboardResponse, DashboardEntity>,
    private val mapperSection: Mapper<SectionResponse, SectionEntity>
) : DataSource {
    override fun getDashboard(): Single<DashboardEntity> {
        return apiService.getDashboard().map {
            mapperDashboard.map(it)
        }
    }

    override fun getSection(link: LinkEntity): Single<SectionEntity> {
        return apiService.getSection(link.href.cleanHref()).map {
            mapperSection.map(it)
        }
    }

    override fun saveDashboard(data: DashboardEntity): Completable {
        throw ErrorEntity.NOT_IMPLEMENTED_REMOTE_DATASOURCE
    }

    override fun saveSection(data: SectionEntity): Completable {
        throw ErrorEntity.NOT_IMPLEMENTED_REMOTE_DATASOURCE
    }
}
