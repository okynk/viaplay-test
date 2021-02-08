package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.LinkResponse
import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.mapper.DashboardResponseEntityMapper
import com.okynk.viaplaytest.mapper.LinkResponseEntityMapper
import com.okynk.viaplaytest.mapper.Mapper
import com.okynk.viaplaytest.mapper.SectionResponseEntityMapper
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<LinkResponse, LinkEntity>> {
        LinkResponseEntityMapper()
    }

    single<Mapper<DashboardResponse, DashboardEntity>> {
        DashboardResponseEntityMapper(get())
    }

    single<Mapper<SectionResponse, SectionEntity>> {
        SectionResponseEntityMapper()
    }
}