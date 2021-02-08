package com.okynk.viaplaytest.feature.screen.sections.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.okynk.viaplaytest.sections

class SectionsEpoxyController : TypedEpoxyController<List<SectionsEpoxyData>>() {
    override fun buildModels(data: List<SectionsEpoxyData>) {
        data.forEach {
            sections {
                id(it.id)
                data(it)
            }
        }
    }
}