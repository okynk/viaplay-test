package com.okynk.viaplaytest.feature.screen.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okynk.viaplaytest.feature.base.BaseViewModel
import com.okynk.viaplaytest.feature.screen.main.epoxy.MainEpoxyData
import com.okynk.viaplaytest.model.DashboardEntity
import com.okynk.viaplaytest.model.ScreenEntity
import com.okynk.viaplaytest.usecase.UseCase
import com.okynk.viaplaytest.util.scheduler.SchedulerProvider

class MainViewModel(
    application: Application,
    scheduler: SchedulerProvider,
    private val useCase: UseCase
) : BaseViewModel(application, scheduler) {

    private val mListData = MutableLiveData<List<MainEpoxyData>>()
    val listData: LiveData<List<MainEpoxyData>> = mListData

    override fun start() {
        getSections()
    }

    private fun getSections() {
        useCase.getDashboard().execute {
            mListData.postValue(mapData(it))
        }
    }

    private fun mapData(data: DashboardEntity): List<MainEpoxyData> {
        return data.sections.map { section ->
            MainEpoxyData(
                id = section.id,
                title = section.title,
                item = section,
                onClick = {
                    mStartActivity.postValue(ScreenEntity.SectionDetail(it))
                }
            )
        }
    }
}