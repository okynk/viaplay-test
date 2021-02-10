package com.okynk.viaplaytest.repository

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.datasource.DataSource
import com.okynk.viaplaytest.mock.mockDashboardEntity
import com.okynk.viaplaytest.mock.mockLinkEntity_1
import com.okynk.viaplaytest.mock.mockSectionEntity
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class RepositoryTest : BaseTest() {

    private lateinit var repository: Repository
    private val local: DataSource = mockk()
    private val remote: DataSource = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        repository = RepositoryImpl(local, remote)
    }

    @Test
    fun getDashboard_noCache() {
        val expected = mockDashboardEntity

        every { local.getDashboard() } returns Single.error(mockk<Throwable>())
        every { remote.getDashboard() } returns Single.just(expected)
        every { local.saveDashboard(expected) } returns Completable.complete()

        repository.getDashboard().test()
            .assertResult(expected)

        verify(exactly = 1) { local.getDashboard() }
        verify(exactly = 1) { remote.getDashboard() }
        verify(exactly = 1) { local.saveDashboard(any()) }
    }

    @Test
    fun getDashboard_hasCache() {
        val expected = mockDashboardEntity

        every { local.getDashboard() } returns Single.just(expected)

        repository.getDashboard().test()
            .assertResult(expected)

        verify(exactly = 1) { local.getDashboard() }
        verify(exactly = 0) { local.saveDashboard(any()) }
        verify { remote wasNot Called }
    }

    @Test
    fun getSection_noCache() {
        val expected = mockSectionEntity
        val link = mockLinkEntity_1

        every { local.getSection(link) } returns Single.error(mockk<Throwable>())
        every { remote.getSection(link) } returns Single.just(expected)
        every { local.saveSection(expected) } returns Completable.complete()

        repository.getSection(link).test()
            .assertResult(expected)

        verify(exactly = 1) { local.getSection(any()) }
        verify(exactly = 1) { remote.getSection(any()) }
        verify(exactly = 1) { local.saveSection(any()) }
    }

    @Test
    fun getSection_hasCache() {
        val expected = mockSectionEntity
        val link = mockLinkEntity_1

        every { local.getSection(link) } returns Single.just(expected)

        repository.getSection(link).test()
            .assertResult(expected)

        verify(exactly = 1) { local.getSection(any()) }
        verify(exactly = 0) { local.saveSection(any()) }
        verify { remote wasNot Called }
    }
}
