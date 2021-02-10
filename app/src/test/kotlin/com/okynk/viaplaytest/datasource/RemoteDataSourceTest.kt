package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.api.ApiService
import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.mapper.Mapper
import com.okynk.viaplaytest.mock.mockDashboardEntity
import com.okynk.viaplaytest.mock.mockDashboardResponse
import com.okynk.viaplaytest.mock.mockLinkEntity_1
import com.okynk.viaplaytest.mock.mockSectionEntity
import com.okynk.viaplaytest.mock.mockSectionResponse
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.ErrorEntity
import com.okynk.viaplaytest.model.SectionEntity
import com.okynk.viaplaytest.util.extensions.cleanHref
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.inject

class RemoteDataSourceTest : BaseTest() {

    private lateinit var dataSource: DataSource
    private val apiService: ApiService = mockk()
    private val mapperDashboard: Mapper<DashboardResponse, DashboardEntity> by inject(named(Mapper.DASHBOARD_RESPONSE_TO_ENTITY))
    private val mapperSection: Mapper<SectionResponse, SectionEntity> by inject(named(Mapper.SECTION_RESPONSE_TO_ENTITY))

    @Before
    override fun setUp() {
        super.setUp()
        dataSource = RemoteDataSource(apiService, mapperDashboard, mapperSection)
    }

    @Test
    fun getDashboard() {
        val response = mockDashboardResponse
        val expected = mockDashboardEntity

        every { apiService.getDashboard() } returns Single.just(response)

        dataSource.getDashboard().test()
            .assertResult(expected)

        verify(exactly = 1) { apiService.getDashboard() }
        verify(exactly = 0) { apiService.getSection(any()) }
    }

    @Test
    fun getSection() {
        val response = mockSectionResponse
        val expected = mockSectionEntity
        val link = mockLinkEntity_1

        every { apiService.getSection(link.href.cleanHref()) } returns Single.just(response)

        dataSource.getSection(link).test()
            .assertResult(expected)

        verify(exactly = 1) { apiService.getSection(any()) }
        verify(exactly = 0) { apiService.getDashboard() }
    }

    @Test(expected = ErrorEntity::class)
    fun saveDashboard() {
        val data = mockDashboardEntity

        dataSource.saveDashboard(data).test()

        verify { apiService wasNot Called }
    }

    @Test(expected = ErrorEntity::class)
    fun saveSection() {
        val data = mockSectionEntity

        dataSource.saveSection(data).test()

        verify { apiService wasNot Called }
    }
}
