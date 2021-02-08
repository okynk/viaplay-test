package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.datasource.DataSource.Companion.LOCAL
import com.okynk.viaplaytest.datasource.DataSource.Companion.REMOTE
import com.okynk.viaplaytest.repository.Repository
import com.okynk.viaplaytest.repository.RepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(local = get(named(LOCAL)), remote = get(named(REMOTE)))
    }
}
