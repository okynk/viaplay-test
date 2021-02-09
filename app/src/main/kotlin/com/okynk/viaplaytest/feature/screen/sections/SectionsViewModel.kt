package com.okynk.viaplaytest.feature.screen.sections

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okynk.viaplaytest.feature.base.BaseViewModel
import com.okynk.viaplaytest.feature.screen.sections.epoxy.SectionsEpoxyData
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.usecase.UseCase
import com.okynk.viaplaytest.util.SingleLiveEvent
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider

class SectionsViewModel(
    application: Application,
    scheduler: SchedulerProvider,
    private val useCase: UseCase
) : BaseViewModel(application, scheduler) {

    private val mListData = MutableLiveData<List<SectionsEpoxyData>>()
    val listData: LiveData<List<SectionsEpoxyData>> = mListData

    private val mOpenDetail = SingleLiveEvent<LinkEntity>()
    val openDetail: LiveData<LinkEntity> = mOpenDetail

    fun start() {
        getSections()
    }

    private fun getSections() {
        useCase.getDashboard().execute {
            mListData.postValue(mapData(it))
        }
    }

    private fun mapData(data: DashboardEntity): List<SectionsEpoxyData> {
        return data.sections.map { section ->
            SectionsEpoxyData(
                id = section.id,
                title = section.title,
                item = section,
                onClick = {
                    mOpenDetail.postValue(it)
                }
            )
        }
    }
}