package com.okynk.viaplaytest.mock

import com.okynk.viaplaytest.model.LinkEntity

val mockLinkEntity_1 = LinkEntity(
    id = mockLinkResponse_1.id,
    title = mockLinkResponse_1.title,
    href = mockLinkResponse_1.href
)

val mockLinkEntity_2 = LinkEntity(
    id = mockLinkResponse_2.id,
    title = mockLinkResponse_2.title,
    href = mockLinkResponse_2.href
)

val mockLinkEntity_list = listOf(
    mockLinkEntity_1,
    mockLinkEntity_2
)