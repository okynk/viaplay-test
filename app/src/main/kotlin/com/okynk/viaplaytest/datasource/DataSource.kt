package com.okynk.viaplaytest.datasource

interface DataSource {
//    fun authAnonymously(): Completable
//    fun signOut(): Completable

    companion object {
        const val LOCAL = "LocalDataSource"
        const val REMOTE = "RemoteDataSource"
    }
}
