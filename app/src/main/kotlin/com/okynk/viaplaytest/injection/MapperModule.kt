package com.okynk.viaplaytest.injection

import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.LinkResponse
import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.mapper.DashboardResponseEntityMapper
import com.okynk.viaplaytest.mapper.LinkResponseEntityMapper
import com.okynk.viaplaytest.mapper.Mapper
import com.okynk.viaplaytest.mapper.Mapper.Companion.DASHBOARD_RESPONSE_TO_ENTITY
import com.okynk.viaplaytest.mapper.Mapper.Companion.LINK_RESPONSE_TO_ENTITY
import com.okynk.viaplaytest.mapper.Mapper.Companion.SECTION_RESPONSE_TO_ENTITY
import com.okynk.viaplaytest.mapper.SectionResponseEntityMapper
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<LinkResponse, LinkEntity>>(named(LINK_RESPONSE_TO_ENTITY)) {
        LinkResponseEntityMapper()
    }

    single<Mapper<DashboardResponse, DashboardEntity>>(named(DASHBOARD_RESPONSE_TO_ENTITY)) {
        DashboardResponseEntityMapper(get(named(LINK_RESPONSE_TO_ENTITY)))
    }

    single<Mapper<SectionResponse, SectionEntity>>(named(SECTION_RESPONSE_TO_ENTITY)) {
        SectionResponseEntityMapper()
    }
}