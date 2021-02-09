package com.okynk.viaplaytest.mapper

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.api.response.SectionResponse
import com.okynk.viaplaytest.mock.mockSectionEntity
import com.okynk.viaplaytest.mock.mockSectionResponse
import com.okynk.viaplaytest.model.SectionEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SectionResponseEntityMapperTest : BaseTest() {

    private lateinit var mapper: Mapper<SectionResponse, SectionEntity>

    @Before
    override fun setUp() {
        super.setUp()
        mapper = SectionResponseEntityMapper()
    }

    @Test
    fun map() {
        val input = mockSectionResponse
        val expected = mockSectionEntity

        val actual = mapper.map(input)

        assertEquals(expected, actual)
    }
}