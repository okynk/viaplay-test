package com.okynk.viaplaytest.feature.screen.main.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.okynk.viaplaytest.main

class MainEpoxyController : TypedEpoxyController<List<MainEpoxyData>>() {
    override fun buildModels(data: List<MainEpoxyData>) {
        data.forEach {
            main {
                id(it.id)
                data(it)
            }
        }
    }
}