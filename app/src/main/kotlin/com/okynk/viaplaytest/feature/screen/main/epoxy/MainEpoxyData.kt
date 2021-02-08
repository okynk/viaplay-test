package com.okynk.viaplaytest.feature.screen.main.epoxy

import com.okynk.viaplaytest.model.LinkEntity

data class MainEpoxyData(
    val id: String,
    val title: String,
    val item: LinkEntity,
    val onClick: Function1<LinkEntity, Unit> = {}
)