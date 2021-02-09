package com.okynk.viaplaytest.mapper

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.api.response.LinkResponse
import com.okynk.viaplaytest.mock.mockLinkEntity_1
import com.okynk.viaplaytest.mock.mockLinkResponse_1
import com.okynk.viaplaytest.model.LinkEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LinkResponseEntityMapperTest : BaseTest() {
    private lateinit var mapper: Mapper<LinkResponse, LinkEntity>

    @Before
    override fun setUp() {
        super.setUp()
        mapper = LinkResponseEntityMapper()
    }

    @Test
    fun map() {
        val input = mockLinkResponse_1
        val expected = mockLinkEntity_1

        val actual = mapper.map(input)

        assertEquals(expected, actual)
    }
}