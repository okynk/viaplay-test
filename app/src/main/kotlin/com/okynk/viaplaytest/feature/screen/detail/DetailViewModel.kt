package com.okynk.viaplaytest.feature.screen.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okynk.viaplaytest.feature.base.BaseViewModel
import com.okynk.viaplaytest.model.SectionEntity
import com.okynk.viaplaytest.usecase.UseCase
import com.okynk.viaplaytest.util.extensions.cleanHref
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider

class DetailViewModel(
    application: Application,
    scheduler: SchedulerProvider,
    private val useCase: UseCase
) : BaseViewModel(application, scheduler) {

    private val mViewData = MutableLiveData(DetailViewData())
    val viewData: LiveData<DetailViewData> = mViewData

    fun start(href: String) {
        getSection(href)
    }

    private fun getSection(href: String) {
        useCase.getSection(href.cleanHref()).execute {
            updateViewData(mapData(it))
        }
    }

    private fun updateViewData(data: DetailViewData) {
        mViewData.postValue(
            mViewData.value?.copy(
                title = data.title,
                description = data.description
            )
        )
    }

    private fun mapData(section: SectionEntity) = DetailViewData(
        title = section.title,
        description = section.description
    )

    data class DetailViewData(
        val title: String? = null,
        val description: String? = null
    )
}