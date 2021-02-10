package com.okynk.viaplaytest.mapper

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.api.response.DashboardResponse
import com.okynk.viaplaytest.api.response.LinkResponse
import com.okynk.viaplaytest.mock.mockDashboardEntity
import com.okynk.viaplaytest.mock.mockDashboardResponse
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.inject

class DashboardResponseEntityMapperTest : BaseTest() {
    private lateinit var mapper: Mapper<DashboardResponse, DashboardEntity>
    private val linkMapper: Mapper<LinkResponse, LinkEntity> by inject(named(Mapper.LINK_RESPONSE_TO_ENTITY))

    @Before
    override fun setUp() {
        super.setUp()
        mapper = DashboardResponseEntityMapper(linkMapper)
    }

    @Test
    fun map() {
        val input = mockDashboardResponse
        val expected = mockDashboardEntity

        val actual = mapper.map(input)

        assertEquals(expected, actual)
    }
}
