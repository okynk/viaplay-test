package com.okynk.viaplaytest.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.feature.screen.sections.SectionsViewModel
import com.okynk.viaplaytest.feature.screen.sections.epoxy.SectionsEpoxyData
import com.okynk.viaplaytest.mock.mockDashboardEntity
import com.okynk.viaplaytest.mock.mockSectionsEpoxyData_list
import com.okynk.viaplaytest.model.MessageDialogEntity
import com.okynk.viaplaytest.usecase.UseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SectionsViewModelTest : BaseTest() {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SectionsViewModel
    private val useCase: UseCase = mockk()
    private val application: Application = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = SectionsViewModel(
            application = application,
            scheduler = testScheduler,
            useCase = useCase
        )
    }

    @Test
    fun start() {
        val dashboard = mockDashboardEntity
        val expected = mockSectionsEpoxyData_list

        val observerListData: Observer<List<SectionsEpoxyData>> = mockk(relaxed = true)
        val observerShowMessageDialog: Observer<MessageDialogEntity> = mockk(relaxed = true)
        val observerShowLoadingOverlay: Observer<Boolean> = mockk(relaxed = true)

        every { useCase.getDashboard() } returns Single.just(dashboard)

        viewModel.listData.observeForever(observerListData)
        viewModel.showLoadingOverlay.observeForever(observerShowLoadingOverlay)
        viewModel.showMessageDialog.observeForever(observerShowMessageDialog)

        viewModel.start()

        verify(exactly = 1) { useCase.getDashboard() }
        verify { observerListData.onChanged(expected) }
        verify(exactly = 0) { observerShowMessageDialog.onChanged(any()) }
        verify(exactly = 1) { observerShowLoadingOverlay.onChanged(true) }
        verify(exactly = 1) { observerShowLoadingOverlay.onChanged(false) }
    }
}
