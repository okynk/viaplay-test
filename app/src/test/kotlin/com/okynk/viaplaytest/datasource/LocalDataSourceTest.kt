package com.okynk.viaplaytest.datasource

import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.database.DashboardDao
import com.okynk.viaplaytest.database.SectionsDao
import com.okynk.viaplaytest.mock.mockDashboardEntity
import com.okynk.viaplaytest.mock.mockLinkEntity_1
import com.okynk.viaplaytest.mock.mockSectionEntity
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest : BaseTest() {

    private lateinit var dataSource: DataSource
    private val dashboardDao: DashboardDao = mockk()
    private val sectionsDao: SectionsDao = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        dataSource = LocalDataSource(dashboardDao, sectionsDao)
    }

    @Test
    fun getDashboard() {
        val expected = mockDashboardEntity

        every { dashboardDao.getDashboard() } returns Maybe.just(expected)

        dataSource.getDashboard().test()
            .assertResult(expected)

        verify(exactly = 1) { dashboardDao.getDashboard() }
        verify(exactly = 0) { dashboardDao.insert(any()) }
        verify { sectionsDao wasNot Called }
    }

    @Test
    fun getSection() {
        val expected = mockSectionEntity
        val link = mockLinkEntity_1

        every { sectionsDao.getSection(link.id) } returns Maybe.just(expected)

        dataSource.getSection(link).test()
            .assertResult(expected)

        verify(exactly = 1) { sectionsDao.getSection(any()) }
        verify(exactly = 0) { sectionsDao.insert(any()) }
        verify { dashboardDao wasNot Called }
    }

    @Test
    fun saveDashboard() {
        val data = mockDashboardEntity

        every { dashboardDao.insert(any()) } returns Completable.complete()

        dataSource.saveDashboard(data).test()
            .assertComplete()

        verify(exactly = 1) { dashboardDao.insert(any()) }
        verify(exactly = 0) { dashboardDao.getDashboard() }
        verify { sectionsDao wasNot Called }
    }

    @Test
    fun saveSection() {
        val data = mockSectionEntity

        every { sectionsDao.insert(any()) } returns Completable.complete()

        dataSource.saveSection(data).test()
            .assertComplete()

        verify(exactly = 1) { sectionsDao.insert(any()) }
        verify(exactly = 0) { sectionsDao.getSection(any()) }
        verify { dashboardDao wasNot Called }
    }
}
