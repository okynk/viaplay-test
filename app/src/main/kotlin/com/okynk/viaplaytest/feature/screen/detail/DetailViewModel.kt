package com.okynk.viaplaytest.feature.screen.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okynk.viaplaytest.feature.base.BaseViewModel
import com.okynk.viaplaytest.model.ErrorEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.model.SectionEntity
import com.okynk.viaplaytest.model.toMessageDialogEntity
import com.okynk.viaplaytest.usecase.UseCase
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider

class DetailViewModel(
    application: Application,
    scheduler: SchedulerProvider,
    private val useCase: UseCase
) : BaseViewModel(application, scheduler) {

    private val mViewData = MutableLiveData(DetailViewData())
    val viewData: LiveData<DetailViewData> = mViewData

    fun start(link: LinkEntity?) {
        getSection(link)
    }

    private fun getSection(link: LinkEntity?) {
        link?.let {
            useCase.getSection(link).execute {
                updateViewData(mapData(it))
            }
        } ?: run {
            mShowMessageDialog.postValue(ErrorEntity.GENERIC.toMessageDialogEntity())
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