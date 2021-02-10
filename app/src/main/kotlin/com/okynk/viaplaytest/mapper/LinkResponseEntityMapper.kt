package com.okynk.viaplaytest.mapper

import com.okynk.viaplaytest.api.response.LinkResponse
import com.okynk.viaplaytest.model.LinkEntity

class LinkResponseEntityMapper : Mapper<LinkResponse, LinkEntity> {
    override fun map(from: LinkResponse): LinkEntity {
        return LinkEntity(
            id = from.id,
            title = from.title,
            href = from.href
        )
    }
}
