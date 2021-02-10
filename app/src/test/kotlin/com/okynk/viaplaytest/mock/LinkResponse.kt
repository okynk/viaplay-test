package com.okynk.viaplaytest.mock

import com.okynk.viaplaytest.api.response.LinkResponse

val mockLinkResponse_1 = LinkResponse(
    id = "35bb8a90-d40e-11e2-8b8b-0800200c9a66",
    title = "Serier",
    href = "https://content.viaplay.se/androiddash-se/serier{?dtg}"
)

val mockLinkResponse_2 = LinkResponse(
    id = "2037b330-d411-11e2-8b8b-0800200c9a66",
    title = "Film",
    href = "https://content.viaplay.se/androiddash-se/film{?dtg}"
)

val mockLinkResponse_list = listOf(
    mockLinkResponse_1,
    mockLinkResponse_2
)
