package com.okynk.viaplaytest.repository

import com.okynk.viaplaytest.datasource.DataSource

class RepositoryImpl(
        private val localDataSource: DataSource,
        private val remoteDataSource: DataSource
) : Repository {
//    override fun authAnonymously(): Completable {
//        return remoteDataSource.authAnonymously()
//    }
//
//    override fun signOut(): Completable {
//        return remoteDataSource.signOut()
//    }
}
