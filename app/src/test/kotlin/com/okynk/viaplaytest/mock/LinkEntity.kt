package com.okynk.viaplaytest.mock

import com.okynk.viaplaytest.model.LinkEntity

val mockLinkEntity_1 = LinkEntity(
    id = "35bb8a90-d40e-11e2-8b8b-0800200c9a66",
    title = "Serier",
    href = "https://content.viaplay.se/androiddash-se/serier{?dtg}"
)

val mockLinkEntity_2 = LinkEntity(
    id = "2037b330-d411-11e2-8b8b-0800200c9a66",
    title = "Film",
    href = "https://content.viaplay.se/androiddash-se/film{?dtg}"
)

val mockLinkEntity_list = listOf(
    mockLinkEntity_1,
    mockLinkEntity_2
)