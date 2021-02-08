package com.okynk.viaplaytest.mapper

interface Mapper<in FROM, TO> {
    fun map(from: FROM): TO

    companion object {
        const val LINK_RESPONSE_TO_ENTITY = "Link Response to Entity"
        const val DASHBOARD_RESPONSE_TO_ENTITY = "Dashboard Response to Entity"
        const val SECTION_RESPONSE_TO_ENTITY = "Section Response to Entity"
    }
}