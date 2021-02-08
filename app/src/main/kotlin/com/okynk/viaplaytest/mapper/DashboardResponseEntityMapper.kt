package com.okynk.viaplaytest.mapper

import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.LinkResponse
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity

class DashboardResponseEntityMapper(
    private val linkMapper: Mapper<LinkResponse, LinkEntity>
) : Mapper<DashboardResponse, DashboardEntity> {
    override fun map(from: DashboardResponse): DashboardEntity {
        return DashboardEntity(
            sections = from.links.sections.map {
                linkMapper.map(it)
            }
        )
    }
}