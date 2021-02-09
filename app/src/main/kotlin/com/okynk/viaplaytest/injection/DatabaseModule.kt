package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        AppDatabase.getDatabase(get())
    }

    single {
        provideDashboardDao(get())
    }

    single {
        provideSectionsDao(get())
    }
}

private fun provideDashboardDao(appDatabase: AppDatabase) = appDatabase.dashboardDao()

private fun provideSectionsDao(appDatabase: AppDatabase) = appDatabase.sectionsDao()