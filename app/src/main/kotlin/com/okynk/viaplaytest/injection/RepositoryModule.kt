package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.repository.Repository
import com.okynk.viaplaytest.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(localDataSource = get(), remoteDataSource = get())
    }
}
