package com.okynk.viaplaytest.mapper

import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.model.SectionEntity

class SectionResponseEntityMapper : Mapper<SectionResponse, SectionEntity> {
    override fun map(from: SectionResponse): SectionEntity {
        return SectionEntity(
            id = from.sectionId,
            title = from.title,
            description = from.description
        )
    }
}