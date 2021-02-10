package com.okynk.viaplaytest.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okynk.viaplaytest.BaseTest
import com.okynk.viaplaytest.feature.screen.detail.DetailViewModel
import com.okynk.viaplaytest.mock.mockDetailViewData
import com.okynk.viaplaytest.mock.mockLinkEntity_1
import com.okynk.viaplaytest.mock.mockSectionEntity
import com.okynk.viaplaytest.model.ErrorEntity
import com.okynk.viaplaytest.model.MessageDialogEntity
import com.okynk.viaplaytest.model.toMessageDialogEntity
import com.okynk.viaplaytest.usecase.UseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest : BaseTest() {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel
    private val useCase: UseCase = mockk()
    private val application: Application = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = DetailViewModel(
            application = application,
            scheduler = testScheduler,
            useCase = useCase
        )
    }

    @Test
    fun start() {
        val link = mockLinkEntity_1
        val section = mockSectionEntity
        val expected = mockDetailViewData

        val observerViewData: Observer<DetailViewModel.DetailViewData> = mockk(relaxed = true)
        val observerShowMessageDialog: Observer<MessageDialogEntity> = mockk(relaxed = true)
        val observerShowLoadingOverlay: Observer<Boolean> = mockk(relaxed = true)

        every { useCase.getSection(link) } returns Single.just(section)

        viewModel.viewData.observeForever(observerViewData)
        viewModel.showLoadingOverlay.observeForever(observerShowLoadingOverlay)
        viewModel.showMessageDialog.observeForever(observerShowMessageDialog)

        viewModel.start(link)

        verify(exactly = 1) { useCase.getSection(any()) }
        verify { observerViewData.onChanged(expected) }
        verify(exactly = 0) { observerShowMessageDialog.onChanged(any()) }
        verify(exactly = 1) { observerShowLoadingOverlay.onChanged(true) }
        verify(exactly = 1) { observerShowLoadingOverlay.onChanged(false) }
    }

    @Test
    fun start_linkNull() {
        val link = null

        val observerViewData: Observer<DetailViewModel.DetailViewData> = mockk(relaxed = true)
        val observerShowMessageDialog: Observer<MessageDialogEntity> = mockk(relaxed = true)
        val observerShowLoadingOverlay: Observer<Boolean> = mockk(relaxed = true)

        viewModel.viewData.observeForever(observerViewData)
        viewModel.showLoadingOverlay.observeForever(observerShowLoadingOverlay)
        viewModel.showMessageDialog.observeForever(observerShowMessageDialog)

        viewModel.start(link)

        verify(exactly = 1) { observerShowMessageDialog.onChanged(ErrorEntity.GENERIC.toMessageDialogEntity()) }
        verify(exactly = 0) { useCase.getSection(any()) }
        verify(exactly = 1) { observerViewData.onChanged(any()) }
        verify(exactly = 0) { observerShowLoadingOverlay.onChanged(any()) }
    }
}
