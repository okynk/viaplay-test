package com.okynk.viaplaytest.api

import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.SectionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    companion object {
        const val DASHBOARD_URL = "androiddash-se"
    }

    @GET(DASHBOARD_URL)
    fun getDashboard(): Single<DashboardResponse>

    @GET
    fun getSection(@Url url: String): Single<SectionResponse>
}