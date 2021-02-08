package com.okynk.viaplaytest.model

sealed class ScreenEntity {
    object SectionList : ScreenEntity()
    data class SectionDetail(val section: LinkEntity) : ScreenEntity()
}
