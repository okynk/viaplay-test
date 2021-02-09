package com.okynk.viaplaytest.repository

import com.okynk.viaplaytest.datasource.DataSource
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val local: DataSource,
    private val remote: DataSource
) : Repository {
    override fun getDashboard(): Single<DashboardEntity> {
        return local.getDashboard().onErrorResumeNext {
            remote.getDashboard().flatMap {
                local.saveDashboard(it).andThen(Single.just(it))
            }
        }
    }

    override fun getSection(link: LinkEntity): Single<SectionEntity> {
        return local.getSection(link).onErrorResumeNext {
            remote.getSection(link).flatMap {
                local.saveSection(it).andThen(Single.just(it))
            }
        }
    }
}
