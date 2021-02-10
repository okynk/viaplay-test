package com.okynk.viaplaytest.usecase

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.mock.mockDashboardEntity
import com.okynk.viaplaytest.mock.mockLinkEntity_1
import com.okynk.viaplaytest.mock.mockSectionEntity
import com.okynk.viaplaytest.repository.Repository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class UseCaseTest : BaseTest() {

    private lateinit var useCase: UseCase
    private val repository: Repository = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        useCase = UseCaseImpl(repository, testScheduler)
    }

    @Test
    fun getDashboard() {
        val expected = mockDashboardEntity

        every { repository.getDashboard() } returns Single.just(expected)

        useCase.getDashboard().test()
            .assertResult(expected)

        verify(exactly = 1) { repository.getDashboard() }
    }

    @Test
    fun getSection() {
        val expected = mockSectionEntity
        val link = mockLinkEntity_1

        every { repository.getSection(link) } returns Single.just(expected)

        useCase.getSection(link).test()
            .assertResult(expected)

        verify(exactly = 1) { repository.getSection(any()) }
    }
}
