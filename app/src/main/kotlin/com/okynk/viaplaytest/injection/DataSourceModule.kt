package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.datasource.DataSource
import com.okynk.viaplaytest.datasource.LocalDataSource
import com.okynk.viaplaytest.datasource.RemoteDataSource
import com.okynk.viaplaytest.mapper.Mapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<DataSource>(named(DataSource.LOCAL)) {
        LocalDataSource(
            dashboardDao = get(),
            sectionsDao = get()
        )
    }

    single<DataSource>(named(DataSource.REMOTE)) {
        RemoteDataSource(
            apiService = get(),
            mapperDashboard = get(named(Mapper.DASHBOARD_RESPONSE_TO_ENTITY)),
            mapperSection = get(named(Mapper.SECTION_RESPONSE_TO_ENTITY))
        )
    }
}
