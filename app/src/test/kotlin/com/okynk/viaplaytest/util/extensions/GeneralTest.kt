package com.okynk.viaplaytest.util.extensions

import com.okynk.viaplaytest.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GeneralTest : BaseTest() {

    @Test
    fun cleanHref() {
        val expected = "https://content.viaplay.se/androiddash-se/serier"
        val actual = "https://content.viaplay.se/androiddash-se/serier{?dtg}".cleanHref()
        assertEquals(expected, actual)
    }
}