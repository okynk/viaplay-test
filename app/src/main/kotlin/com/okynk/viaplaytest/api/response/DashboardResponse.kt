package com.okynk.viaplaytest.api.response

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    val title: String,
    val description: String,
    @SerializedName("_links") val links: LinksResponse
)
