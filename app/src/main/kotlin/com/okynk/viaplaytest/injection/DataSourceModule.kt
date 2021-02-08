package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.datasource.DataSource
import com.okynk.viaplaytest.datasource.LocalDataSource
import com.okynk.viaplaytest.datasource.RemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<DataSource>(named(DataSource.LOCAL)) {
        LocalDataSource()
    }

    single<DataSource>(named(DataSource.REMOTE)) {
        RemoteDataSource(
            apiService = get(),
            mapperDashboard = get(),
            mapperSection = get()
        )
    }
}
