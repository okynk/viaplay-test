package com.okynk.viaplaytest.api.response

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("viaplay:sections") val sections: List<LinkResponse>
)