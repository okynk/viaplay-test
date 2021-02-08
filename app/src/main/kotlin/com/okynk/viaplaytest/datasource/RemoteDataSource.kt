package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.api.ApiService
import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.mapper.Mapper
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.SectionEntity
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

    override fun getSection(href: String): Single<SectionEntity> {
        return apiService.getSection(href).map {
            mapperSection.map(it)
        }
    }
}
