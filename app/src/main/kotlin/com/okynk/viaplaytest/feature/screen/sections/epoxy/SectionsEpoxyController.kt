package com.okynk.viaplaytest.feature.screen.sections.epoxy

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.okynk.viaplaytest.model.LinkEntity
import com.okynk.viaplaytest.sections

class SectionsEpoxyController(
    private val onItemClicked: (LinkEntity) -> Unit
) : TypedEpoxyController<List<SectionsEpoxyData>>() {
    override fun buildModels(data: List<SectionsEpoxyData>) {
        data.forEach { section ->
            sections {
                id(section.id)
                data(section)
                click(View.OnClickListener {
                    onItemClicked(section.item)
                })
            }
        }
    }
}